package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ComputerPropertiesRest;
import org.springframework.expression.spel.ast.LongLiteral;

import java.util.List;

public interface ComputerPropertiesService {

    List<ComputerPropertiesRest> findByRefId(long refId);

}
