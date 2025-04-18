# ATM Report Service

This ATM Report Service is designed to process transactions/logs from different ATMs (A, B, C, D), which generate transactions in various formats (XML, CSV, JSON, YAML) daily. The service generates reports based on the transaction data and exposes them as REST APIs.

## Features

- Supports multiple transaction file formats: **XML, CSV, JSON, YAML**
- Exposes REST APIs to:
  - Get daily transaction summaries
  - Get ATM-specific summaries
  - Get transaction-type-based summaries
- Supported ATM IDs: `A`, `B`, `C`, `D`
- Supported Transaction Types: `WITHDRAW`, `DEPOSIT`, `BALANCE_TRANSFER`

## Technology Stack

- Java 21
- Spring Boot 3
- PostgreSQL

## Example Transaction Data

### CSV Format

transactionId,transactionDate,cardNumber,transactionType,amount,destinationCardNumber 12345,2025-02-05T14:30:00,1234-5678-9876-5432,DEPOSIT,150.75, 12346,2025-02-05T15:00:00,1234-5678-9876-5432,BALANCE_TRANSFER,500.00,9876-5432-1234-5678

### JSON Format
```json
[
  {
    "transactionId": "12345",
    "transactionDate": "2025-02-05T14:30:00",
    "cardNumber": "1234-5678-9876-5432",
    "transactionType": "DEPOSIT",
    "amount": 150.75,
    "destinationCardNumber": ""
  }
]

