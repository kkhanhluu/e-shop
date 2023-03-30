import { credentials } from '@grpc/grpc-js';
import { ReviewServiceClient } from '../../../generated/proto/ReviewService';

export const reviewServiceClient = new ReviewServiceClient(
  process.env.REVIEW_SERVICE_GRPC_URI as string,
  credentials.createInsecure()
);
