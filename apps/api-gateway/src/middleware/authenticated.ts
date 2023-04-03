import { URL } from 'node:url';
import { TRPCError } from '@trpc/server';
import { middleware, publicProcedure } from '../context';

const isAuthenticated = middleware(async ({ ctx, next }) => {
  const accessToken = ctx.req.headers['authorization']?.split(' ')[1];
  if (!accessToken) {
    throw new TRPCError({ code: 'UNAUTHORIZED' });
  }

  try {
    const userInfoEndpoint = new URL(
      '/userinfo',
      process.env.AUTHORIZATION_SERVICE_URI as string
    ).toString();
    const { id, role, email, name } = await (
      await fetch(userInfoEndpoint, {
        headers: { Authorization: `Bearer ${accessToken}` },
      })
    ).json();
    return next({
      ctx: {
        ...ctx,
        user: {
          id,
          role,
          email,
          name,
        },
      },
    });
  } catch (err) {
    console.error(err);
    throw new TRPCError({ code: 'INTERNAL_SERVER_ERROR' });
  }
});

export const authenticatedProcedure = publicProcedure.use(isAuthenticated);
