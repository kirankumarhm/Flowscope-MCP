# FlowScope MCP Server

**Model Context Protocol (MCP) server for FlowScope reverse-engineering and code intelligence.**

FlowScope MCP Server runs as a standalone tool provider enabling AI coding agents (Claude Desktop, Cursor, Antigravity, Windsurf, Cline) to query codebase architecture, trace call graphs, generate Mermaid sequence diagrams, and explore cross-service topologies directly — completely offline using pure JVM parsing engines (JavaParser and tree-sitter).

---

## Features & Capabilities

- **Zero External Dependencies:** Pure-JVM parsing engine (JavaParser for Java, tree-sitter for Go and Python). No external toolchains or daemons required.
- **Dual Transports:**
  - **STDIO Transport:** For desktop AI assistants launching FlowScope MCP as a local subprocess.
  - **HTTP / SSE Transport:** For networked AI agents connecting to Server-Sent Events streams.
- **Rich Analysis Tools:** 9 tools covering control flow graphs, step-by-step walkthroughs, Mermaid sequence diagrams, Spring bean component maps, layered architecture, and workspace service maps.
- **Workflows & Prompts:** Built-in MCP Prompts for guided API endpoint auditing and architecture reviews.

---

## Available MCP Tools

| Tool | Parameters | Description |
|---|---|---|
| `flowscope_analyze` | `path` | Index and parse a codebase into IR nodes, edges, and function CFGs. |
| `flowscope_flow` | `path`, `functionId`, `maxDepth?` | Get inter-procedural CFG with inlined callee methods. |
| `flowscope_flow_description` | `path`, `functionId`, `maxDepth?` | Step-by-step human-readable execution walkthrough. |
| `flowscope_sequence` | `path`, `functionId`, `maxDepth?` | Complete Mermaid `sequenceDiagram` markdown with participant list. |
| `flowscope_component` | `path` | Spring beans classified by layer with dependency injection & call edges. |
| `flowscope_architecture` | `path` | Layered architecture view with datastores, Kafka topics, and external APIs. |
| `flowscope_servicemap` | `path`, `exclude?` | Multi-service workspace topology across Java, Go, Python, and TypeScript. |
| `flowscope_business_logic` | `path`, `functionId`, `maxDepth?` | Narrative explanation of business intent, rules enforced, and phases. |
| `flowscope_llm_status` | none | Check whether an external LLM is configured for narrative generation. |

---

## Quick Start

### 1. Build the Executable JAR

**Prerequisites:** JDK 21+ and Maven.

```bash
mvn clean package
```
This builds `target/flowscope-mcp.jar`.

---

### 2. Configure Your AI Client

#### Claude Desktop (`claude_desktop_config.json`)

On macOS: `~/Library/Application Support/Claude/claude_desktop_config.json`  
On Windows: `%APPDATA%\Claude\claude_desktop_config.json`

```json
{
  "mcpServers": {
    "flowscope": {
      "command": "java",
      "args": [
        "-jar",
        "/absolute/path/to/flowscope-mcp/target/flowscope-mcp.jar"
      ]
    }
  }
}
```

#### Cursor / Antigravity / Windsurf (SSE Transport)

Start the MCP server with the HTTP/SSE transport:
```bash
java -jar target/flowscope-mcp.jar --server
```

Connect your MCP client to:
```
http://localhost:8081/mcp/sse
```

---

## Built-in MCP Prompts

The server registers standard MCP Prompts for AI agents:
1. `audit-endpoint`: Prompts the AI agent to run `flowscope_flow_description`, `flowscope_sequence`, and `flowscope_business_logic` to perform a 360-degree audit of an API endpoint.
2. `review-architecture`: Prompts the AI agent to run `flowscope_architecture` and `flowscope_component` to inspect layer integrity, circular dependencies, and fan-in/fan-out metrics.

---

## Related Projects

- **FlowScope Web**: [https://github.com/kirankumarhm/Flowscope](https://github.com/kirankumarhm/Flowscope) — The interactive web UI (React + Spring Boot) for visual browsing and diagram export.
