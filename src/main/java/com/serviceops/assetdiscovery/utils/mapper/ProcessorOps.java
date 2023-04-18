package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Processor;
import com.serviceops.assetdiscovery.rest.ProcessorRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class ProcessorOps extends AssetBaseOps<Processor, ProcessorRest> {
    private final Processor processor;
    private final ProcessorRest  processorRest;

    public ProcessorOps( Processor processor, ProcessorRest processorRest) {
        super(processor, processorRest);
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
        processor.setL1CacheSize(processorRest.getL1CacheSize());
        processor.setL2CacheSize(processorRest.getL2CacheSize());
        processor.setL3CacheSize(processorRest.getL3CacheSize());
        processor.setFamily(processorRest.getFamily());

        return processor;
    }

    public ProcessorRest entityToRest() {
        super.entityToRest(processor);
        processorRest.setProcessorName(processor.getProcessorName());
        processorRest.setDescription(processor.getDescription());
        processorRest.setWidth(processor.getWidth());
        processorRest.setCpuSpeed(processor.getCpuSpeed());
        processorRest.setCoreCount(processor.getCoreCount());
        processorRest.setL1CacheSize(processor.getL1CacheSize());
        processorRest.setL2CacheSize(processor.getL2CacheSize());
        processorRest.setL3CacheSize(processor.getL3CacheSize());
        processorRest.setFamily(processor.getFamily());

        return processorRest;
    }
}
