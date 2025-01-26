package com.smarthome.platform.upc.analytics.interfaces.rest;

import com.smarthome.platform.upc.analytics.domain.services.PerformanceIndicatorCommandService;
import com.smarthome.platform.upc.analytics.interfaces.rest.resources.CreatePerformanceIndicatorResource;
import com.smarthome.platform.upc.analytics.interfaces.rest.resources.PerformanceIndicatorResource;
import com.smarthome.platform.upc.analytics.interfaces.rest.transform.CreatePerformanceIndicatorCommandFromResourceAssembler;
import com.smarthome.platform.upc.analytics.interfaces.rest.transform.PerformanceIndicatorResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/performance-indicators", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Performance Indicators", description = "Endpoints for managing performance indicators")
public class PerformanceIndicatorsController {
    private final PerformanceIndicatorCommandService performanceIndicatorCommandService;

    public PerformanceIndicatorsController(PerformanceIndicatorCommandService performanceIndicatorCommandService) {
        this.performanceIndicatorCommandService = performanceIndicatorCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new performance indicator", description = "Create a new performance indicator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Performance indicator created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<PerformanceIndicatorResource> createPerformanceIndicator(@RequestBody CreatePerformanceIndicatorResource resource) {
        var command = CreatePerformanceIndicatorCommandFromResourceAssembler.toCommandFromResource(resource);
        var performanceIndicator = performanceIndicatorCommandService.handle(command);
        if (performanceIndicator.isEmpty()) return ResponseEntity.badRequest().build();
        var createdPerformanceIndicator = performanceIndicator.get();
        var performanceIndicatorResource = PerformanceIndicatorResourceFromEntityAssembler.toResourceFromEntity(createdPerformanceIndicator);
        return ResponseEntity.ok(performanceIndicatorResource);
    }
}
