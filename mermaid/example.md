# Kafka Mermaid Example

```mermaid
flowchart LR

    P[Producer]

    subgraph Kafka Topic: topic1
        P0["Partition 0
            offsets: 0,1,2"]
        P1["Partition 1
            offsets: 0,1,2,3,4,5"]
    end

    P -->|"Event1"| P0
    P -->|"Event2"| P1

    subgraph Consumer Group1
        Consumer1[Consumer1]
        Group1Off["Committed Offsets<br/>P0=1<br/>P1=3"]
    end

    subgraph Consumer Group2
        Consumer2[Consumer2]
        Consumer3[Consumer3]
        Group2Off["Committed Offsets<br/>P0=2<br/>P1=5"]
    end

    P0 --> Consumer1
    P1 --> Consumer1
    Consumer1 --> Group1Off

    P0 --> Consumer2
    P1 --> Consumer3
    Consumer2 --> Group2Off
    Consumer3 --> Group2Off
```
