# Zadatak: Fraud Detection sustav

```mermaid
flowchart LR

    L[Login Service]
    S[Security Service]
    P[Payment Service]

    subgraph Kafka Topic: logins
        L0["Partition 0
            offsets: 0,1,2,3"]
    end

    subgraph Kafka Topic: security-events
        S0["Partition 0
            offsets: 0,1,2"]
    end

    subgraph Kafka Topic: payments
        P0["Partition 0
            offsets: 0,1,2,3,4"]
    end

    L -->|"UserLoggedIn"| L0
    S -->|"PasswordChanged"| S0
    P -->|"PaymentRequested"| P0

    subgraph Fraud Detection Group
        FD[Fraud Detection Consumer]
        LOff["Logins Committed Offsets</br>P0=3"]
        SOff["Security Committed Offsets</br>P0=2"]
        POff["Payment Committed Offsets</br>P0=4"]
    end

    L0 --> FD
    S0 --> FD
    P0 --> FD

    FD --> LOff
    FD --> SOff
    FD --> POff
```

## Povećanje broja resursa

Prvi topic - 2 particije

Drugi topic - 3 particije

Treci topic - 2 particije

3 Consumera
