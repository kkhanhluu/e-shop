import { credentials } from '@grpc/grpc-js';
import { ReviewServiceClient } from '../../../generated/proto/ReviewService';

export const reviewServiceClient = new ReviewServiceClient(
  'localhost:9094',
  credentials.createInsecure()
);
