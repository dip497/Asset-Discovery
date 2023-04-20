package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.MotherBoardOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MotherBoardServiceImpl implements MotherBoardService {
    private final CustomRepository customRepository;
    private final Logger logger = LoggerFactory.getLogger(MotherBoardServiceImpl.class);

    public MotherBoardServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    @Override
    public void save(Long refId) {
        Optional<MotherBoard> fetchMotherBoard =
                customRepository.findByColumn("refId", refId, MotherBoard.class);
        if (fetchMotherBoard.isPresent()) {
            List<String> parseResult = getParseResult();
            MotherBoard motherBoard = fetchMotherBoard.get();
            motherBoard.setManufacturer(parseResult.get(0));
            motherBoard.setSerialNumber(parseResult.get(1));
            motherBoard.setVersion(parseResult.get(2));
            customRepository.save(motherBoard);
            logger.debug("Updated MotherBoard with refId -> {}", motherBoard.getRefId());

        } else {
            MotherBoard motherBoard = new MotherBoard();
            motherBoard.setRefId(refId);
            List<String> parseResult = getParseResult();
            motherBoard.setManufacturer(parseResult.get(0));
            motherBoard.setSerialNumber(parseResult.get(1));
            motherBoard.setVersion(parseResult.get(2));
            customRepository.save(motherBoard);
            logger.debug("Saved MotherBoard with refId -> {}", motherBoard.getRefId());
        }
    }

    @Override
    public List<MotherBoardRest> findByRefId(Long refId) {

        Optional<MotherBoard> motherBoardOptional =
                customRepository.findByColumn("refId", refId, MotherBoard.class);

        if (motherBoardOptional.isPresent()) {
            MotherBoardRest motherBoardRest = new MotherBoardRest();
            MotherBoardOps motherBoardOps = new MotherBoardOps(motherBoardOptional.get(), motherBoardRest);
            logger.info("MotherBoard fetched with Asset Id -> {}", refId);
            List<MotherBoardRest> motherBoardRestList = new ArrayList<>();
            motherBoardRestList.add(motherBoardOps.entityToRest());

            return motherBoardRestList;
        } else {
            logger.error("Motherboard not found with Asset Id -> {}", refId);
            return List.of();
        }

    }

    @Override
    public void update(Long refId, MotherBoardRest motherBoardRest) {
        Optional<MotherBoard> fetchMotherboard =
                customRepository.findByColumn("refId", refId, MotherBoard.class);
        if (fetchMotherboard.isEmpty()) {
            logger.error("Motherboard not found with Asset Id -> {}", refId);
            throw new ComponentNotFoundException("MotherBoard", "refId", refId);
        } else {

            MotherBoard motherBoard = fetchMotherboard.get();
            MotherBoardOps motherBoardOps = new MotherBoardOps(motherBoard, motherBoardRest);
            customRepository.save(motherBoardOps.restToEntity());
            logger.info("MotherBoard Updated with Asset Id ->{}", motherBoard.getRefId());
        }
    }

    @Override
    public void deleteByRefId(Long refId) {
        customRepository.deleteById(MotherBoard.class, refId, "refId");
        logger.info("MotherBoard deleted with Asset Id ->{}", refId);
    }

    private void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        commands.put("sudo dmidecode --string baseboard-manufacturer", new String[] {});
        commands.put("sudo dmidecode --string baseboard-serial-number", new String[] {});
        commands.put("sudo dmidecode --string baseboard-version", new String[] {});
        LinuxCommandExecutorManager.add(MotherBoard.class, commands);
    }

    private List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(MotherBoard.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            Collections.addAll(list, values);
        }
        return list;
    }

}
