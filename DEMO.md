# Finance Assist Demo Script

## Project Overview
This project is a Spring Boot application that serves as a finance assistant. It uses OpenAI's GPT model for generating responses and Chroma as a vector store for efficient information retrieval.

## Setup
1. Ensure Docker is installed on your system.
2. Run the following command to start Chroma and Jaeger:
   ```
   docker-compose up -d
   ```
3. Set the `OPENAI_KEY` environment variable with your OpenAI API key.
4. Run the Spring Boot application.

## Demo Script

### Positive Scenarios

1. **Ingesting Financial Information**
    - Endpoint: POST /finance/assist/save
    - Action: Send a POST request with a list of URLs containing financial information.
    - Payload
      ```
      [
        "https://dashdevs.com/blog/fintech-trends-2024/"
      ]
      ```
    - Expected Result: 200 OK response, indicating successful ingestion.

2. **Asking a Simple Financial Question**
    - Endpoint: GET /finance/assist/search
    - Action: Send a GET request with a financial question.
   ```
        What is embedded finance?
   ```
    - Expected Result: A relevant answer based on the ingested information.

3. **Asking a Complex Financial Question**
    - Endpoint: GET /finance/assist/search
    - Action: Send a GET request with a more complex financial question.
   ```
      how big is BNPL market?
   ```
   ```
   [
    "https://explodingtopics.com/blog/fintech-trends"
   ]
   ```
    - Expected Result: A detailed explanation drawing from multiple ingested sources.

### Negative Scenarios

1. **Ingesting Invalid URLs**
    - Endpoint: POST /finance/assist/save
    - Action: Send a POST request with invalid or non-existent URLs.
   ```
   https://invalid-url.com/article
   ```
    - Expected Result: Error response indicating failure to ingest invalid URLs.

2. **Asking a Question Without Prior Ingestion**
    - Endpoint: GET /finance/assist/search
    - Action: Send a GET request with a question before ingesting any data.
   ```
   how to calculate XIRR? Explain by giving an example.
   ```
   ```
   [
    "https://plaid.com/resources/fintech/fintech-trends/",
    "https://www.etmoney.com/learn/mutual-funds/what-is-xirr-in-mutual-funds/",
    "https://groww.in/p/xirr-in-mutual-funds"
   ]
   ```
    - Expected Result: A response indicating insufficient data or suggesting to ingest information first.

3. **Asking an Off-Topic Question**
    - Endpoint: GET /finance/assist/search
    - Action: Send a GET request with a question unrelated to finance.
   ```
   what is the temperature in sf?
   ```
    - Expected Result: A response indicating that the question is out of scope or suggesting to ask finance-related questions.

## Non-Functional Requirements Implemented

1. **API Documentation**:
    - Swagger UI is configured (springdoc.swagger-ui.path=/swagger-ui.html)
    - Access the API documentation at: http://localhost:8080/swagger-ui.html

2. **Observability**:
    - Health checks and metrics exposed (management.endpoints.web.exposure.include=*)
    - Detailed health information available (management.endpoints.health.show-details=always)

3. **Distributed Tracing**:
    - OpenTelemetry tracing enabled (management.tracing.sampling.probability=1)
    - Jaeger configured as the tracing backend (management.otlp.tracing.endpoint=http://localhost:4318/v1/traces)

4. **Performance**:
    - Virtual threads enabled for improved concurrency (spring.threads.virtual.enabled=true)

5. **Logging and Monitoring**:
    - Extensive logging for AI operations, including input, prompts, completions, and errors

6. **Scalability**:
    - Chroma vector store used for efficient information retrieval and scaling

7. **Security**:
    - OpenAI API key stored as an environment variable for better security practices

To fully demonstrate these non-functional aspects:
- Show the Swagger UI for API documentation
- Access the /actuator endpoints to demonstrate health checks and metrics
- Use the Jaeger UI (http://localhost:16686) to show distributed tracing
- Demonstrate the application's responsiveness under load, showcasing the benefits of virtual threads
- Show the logging output, highlighting the detailed AI operation logs
- Discuss the scalability benefits of using Chroma as a vector store

This demo script covers the main functionality of your Finance Assist application while also highlighting the implemented non-functional requirements.