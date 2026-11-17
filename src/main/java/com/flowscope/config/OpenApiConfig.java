package com.flowscope.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI 3 metadata for the FlowScope REST API. The generated spec is served at
 * {@code /v3/api-docs} and the interactive Swagger UI at {@code /swagger-ui.html}.
 */
@Configuration
public class OpenApiConfig {

    @Value("${flowscope.version:0.1.0}")
    private String version;

    @Bean
    public OpenAPI flowScopeOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("FlowScope MCP Server API")
                        .version(version)
                        .description("""
                                FlowScope Model Context Protocol (MCP) Server enables AI coding agents (Claude, Cursor, Windsurf, Antigravity)
                                to query codebase architecture, trace call graphs, generate Mermaid sequence diagrams, and explore cross-service topologies.

                                Transport endpoints:
                                - **SSE Stream** (`/mcp/sse`) — Persistent Server-Sent Events stream for AI agents.
                                - **Message Endpoint** (`/mcp/message`) — JSON-RPC 2.0 message dispatch endpoint.
                                """)
                        .contact(new Contact().name("FlowScope"))
                        .license(new License().name("Proprietary")))
                .servers(List.of(new Server().url("/").description("This server")));
    }
}
