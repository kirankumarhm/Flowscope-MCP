package com.flowscope;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * FlowScope Model Context Protocol (MCP) Server application entry point.
 * <p>
 * Supports dual transports:
 * <ul>
 *     <li><b>STDIO:</b> Communicates over standard input/output with desktop AI agents (Claude Desktop, Cursor, Antigravity).</li>
 *     <li><b>HTTP / SSE:</b> Serves Server-Sent Events and HTTP endpoints on /mcp/sse and /mcp/message.</li>
 * </ul>
 */
@SpringBootApplication
public class FlowScopeMcpApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FlowScopeMcpApplication.class);

        // Turn banner off so STDOUT remains clean JSON-RPC for MCP agents
        app.setBannerMode(Banner.Mode.OFF);

        app.run(args);
    }
}
