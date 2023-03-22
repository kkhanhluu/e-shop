package eshop.inventoryservice.grpc;

import eshop.api.exceptions.NotFoundException;
import eshop.inventoryService.grpc.GetInventoryByProductIdRequest;
import eshop.inventoryService.grpc.Inventory;
import eshop.inventoryService.grpc.InventoryServiceGrpc;
import eshop.inventoryservice.entities.ProductInventory;
import eshop.inventoryservice.service.InventoryService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class InventoryServiceProtoServiceImpl extends InventoryServiceGrpc.InventoryServiceImplBase {
    private final InventoryService inventoryService;

    @Override
    public void getInventoryByProductId(GetInventoryByProductIdRequest request,
                                        StreamObserver<Inventory> responseObserver) {
        ProductInventory productInventory = inventoryService.getInventoryByProductId(
                UUID.fromString(request.getProductId())).orElseThrow(
                NotFoundException::new);

        Inventory response = Inventory.newBuilder()
                .setProductId(productInventory.getProductId().toString())
                .setId(productInventory.getId().toString())
                .setQuantityOnHand(productInventory.getQuantityOnHand().intValue())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}