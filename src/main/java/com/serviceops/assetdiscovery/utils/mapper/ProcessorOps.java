package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Processor;
import com.serviceops.assetdiscovery.rest.ProcessorRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class ProcessorOps extends AssetBaseOps<Processor, ProcessorRest> {
    private final Processor processor;
    private final ProcessorRest  processorRest;

    public ProcessorOps(Processor assetBase, ProcessorRest assetBaseRest, Processor processor, ProcessorRest processorRest) {
        super(assetBase, assetBaseRest);
        this.processor = processor;
        this.processorRest = processorRest;
    }


    public Processor restToEntity() {
        super.restToEntity(processorRest);
        processor.setProcessorName(processorRest.getProcessorName());
        processor.setDescription(processorRest.getDescription());
        processor.setWidth(processorRest.getWidth());
        processor.setCpuSpeed(processorRest.getCpuSpeed());
        processor.setCoreCount(processorRest.getCoreCount());
        processor.setExternalClock(processorRest.getExternalClock());
        processor.setL1CacheSize(processorRest.getL1CacheSize());
        processor.setL2CacheSize(processorRest.getL2CacheSize());
        processor.setL3CacheSize(processorRest.getL3CacheSize());
        processor.setFamily(processorRest.getFamily());
        processor.setDeviceId(processorRest.getDeviceId());
        processor.setSocketDesignation(processorRest.getSocketDesignation());

        return processor;
    }
}
