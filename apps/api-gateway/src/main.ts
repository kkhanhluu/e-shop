import express from 'express';
import * as path from 'path';
import * as trpcExpress from '@trpc/server/adapters/express';
import { expressHandler } from 'trpc-playground/handlers/express';
import { appRouter } from './router';
import { createContext } from './context';

async function main() {
  const app = express();

  app.use('/assets', express.static(path.join(__dirname, 'assets')));

  app.get('/api', (req, res) => {
    res.send({ message: 'Welcome to api-gateway!' });
  });

  app.use(
    '/trpc',
    trpcExpress.createExpressMiddleware({
      router: appRouter,
      createContext,
    })
  );

  if (process.env.NODE_ENV === 'development') {
    app.use(
      '/trpc-playground',
      await expressHandler({
        trpcApiEndpoint: '/trpc',
        router: appRouter,
        playgroundEndpoint: '/trpc-playground',
      })
    );
  }

  const port = process.env.PORT || 3333;
  const server = app.listen(port, () => {
    console.log(`Listening at http://localhost:${port}/api`);
  });
  server.on('error', console.error);
}

main();
