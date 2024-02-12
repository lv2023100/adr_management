  ## ADR（Architecture Decision Record，架構決策紀錄）
  ## Introduce
是一種用於記錄系統或軟體項目中架構決策的文件。它記錄了在系統設計或開發過程中做出的重要架構性決策，並提供了背景、原因、決策和結果的詳細說明。ADR有助於團隊成員理解和記錄決策的脈絡，並提供一個參考點，以便日後查詢和回顧。它們通常是由團隊共同創建和維護的，並在項目的整個生命周期中持續更新。ADR的內容和結構可以根據組織的需求和慣例而有所不同。
- **作品連結**: [ADR架構決策紀錄作品連結](http://64.176.44.232/)

  ## 使用的技術

- **OpenJDK 21**：使用 OpenJDK 21 進行開發，這是 Java 平台的開源實現。
- **Spring Boot**：使用 Spring Boot 創建獨立的、生產級的 Spring 應用程式。
- **gRPC**：gRPC 是由 Google 開發的高性能、開源的通用 RPC 框架，用於服務間通信。
- **Axon Framework**：Axon Framework 是一個 Java 框架，提供了構建可擴展、可靠和事件驅動的應用程式所需的基本組件。
- **Saga**：Axon Saga 是 Axon Framework 的一個功能，用於管理長時間運行的分佈式事務。
- **CQRS（命令查詢責任分離）**：CQRS 是一種將讀取和寫入操作分開的架構模式，提供更好的可擴展性和性能。
- **Onion Architecture**：洋蔥架構是一種軟體架構模式，它以圍繞業務核心的中心模型結構化代碼，外部圈層包含輸入/輸出邏輯，內部圈層包含業務邏輯。
- **Kubernetes**：Kubernetes 是一個用於自動部署、擴展和管理容器化應用程式的開源平台，我們將使用它來部署和管理我們的微服務應用程式。
- **Istio**：Istio 是一個開源服務網格平台，用於連接、監視和保護微服務之間的通信。
  
 ## 這ADR示例項目嘗試導入了 Domain-driven design（DDD）的概念，介紹如下。
 ## 使用者故事
- ADR是做決議的系統做紀錄，公司Team底下的Memeber們可以覺得為這個Team決定修改或建立這架構並做紀錄，之後如果要修改後可以知道當時情況並推測更改後的風險。
- 當有專案需要創建Team時，有Admin權限的Member可以新增Team跟Member，在Team底下的Member只能修改自己Team的資料。
- 當Team底下的Member也可以新增其他人加入，但Member只能編輯自己Team的資料。
- 當有系統架構會議的時候Member可以根據他所屬的Team，透過架構決策紀錄平台紀錄當下架構決策的上下文、決策、結果，每一次的編輯都會有紀錄快照。

 ## 根據以上提取Ubiquitous Language
- **ADR Record:** 一種文檔，用於記錄關於架構決策的上下文、決策、結果，以及可能帶來的影響。 
- **Team (團隊):** 根據專案的需求創建Team，並可以新增Member。
- **Member (成員):** Team底下的(Member)成員，具有特定的角色和責任，參與或決定ADR架構決策和專案開發。 
- **ADR Historical:** 對資料的某一版本進行保存的狀態，以便於未來追溯和理解資料的變更歷史。 架構


## 子域（Subdomains）

### ADR Record 核心子領域

在我們的系統中，ADR Record 子域負責管理和記錄架構決策紀錄（Architecture Decision Records，ADR）。這包括創建、存儲、查詢和更新有關我們系統架構決策的信息，並可以用ADR Historical查找修改紀錄。

### Team 支持子領域

Team 子域代表我們組織中的團隊。這包括團隊的創建、管理和分配，以及團隊之間的協作和溝通。

### Member 支持子領域

Member 子域負責管理我們組織中的成員信息。這包括成員的註冊、個人資料、權限管理等。

