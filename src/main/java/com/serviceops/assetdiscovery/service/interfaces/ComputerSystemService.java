package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import org.springframework.expression.spel.ast.LongLiteral;

import java.util.List;

public interface ComputerSystemService {

    void save(long Id);

    List<ComputerSystemRest> get(long id);

    void deleteById(long id);

    void update(long refId, ComputerSystemRest computerSystemRest);

}
