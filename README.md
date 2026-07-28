# BuySmartAI

Smart Ecommerce web using Spring AI and React — a modern e‑commerce platform enhanced with Retrieval-Augmented Generation (RAG), generative AI features, and a conversational chatbot for product discovery and support.

---

Table of Contents
- [Key Features](#key-features)
- [Demo / Screenshots](#demo--screenshots)
- [Tech Stack](#tech-stack)
- [Repository Layout (suggested)](#repository-layout-suggested)
- [Prerequisites](#prerequisites)
- [Environment Variables](#environment-variables)
- [Local Setup (Backend & Frontend)](#local-setup-backend--frontend)
  - [Backend (Spring AI)](#backend-spring-ai)
  - [Frontend (React)](#frontend-react)
- [Using the Chatbot & RAG](#using-the-chatbot--rag)
- [API Examples](#api-examples)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## Key Features
- Product catalog and browsing UI built with React.
- Backend services implemented with Spring (Java) and Spring AI extensions (for AI-driven flows).
- Retrieval-Augmented Generation (RAG) for answering product and policy queries using a vector store + embeddings.
- Generative AI features for product descriptions, recommendations, and search refinement.
- Conversational chatbot with context-aware product suggestions and support.
- Extensible connector points for different LLM providers and vector databases.

## Demo / Screenshots
(Add screenshots or a demo gif here — place them in `docs/` or the repo root and link them.)

## Tech Stack
- Frontend: React, JavaScript/TypeScript (depending on implementation)
- Backend: Java, Spring Boot, Spring AI
- AI / Vector: OpenAI / other LLM provider (configurable), vector DB (e.g., Pinecone, FAISS, Milvus — configurable)
- Build tools: Maven (backend), npm / yarn (frontend)

## Repository Layout (suggested)
Your repo may already mirror this layout. If not, adapt commands below to your structure.
- `/backend/` — Spring Boot application (Java)
- `/frontend/` — React application (JS/TS)
- `/docs/` — screenshots, architecture diagrams
- `/infra/` — Docker / docker-compose / k8s manifests (optional)

## Prerequisites
- Java 17+ (or the version your Spring Boot app requires)
- Maven 3.6+
- Node.js 16+ and npm or Yarn
- (Optional) Docker & docker-compose for containerized local runs
- AI provider API key (OpenAI, Azure, Anthropic, etc.)
- Vector DB credentials (Pinecone API key, or local FAISS setup)

## Environment Variables
Create environment files for both backend and frontend as needed. Example variables:

Backend (.env or application.properties / application.yaml)
- OPENAI_API_KEY — API key for your LLM provider (or provider-specific keys)
- VECTOR_DB_URL — URL/endpoint for your vector database (if using hosted vector DB)
- VECTOR_DB_API_KEY — API key for your vector database (if required)
- DATABASE_URL — JDBC connection string for your relational DB (if used)
- SPRING_PROFILES_ACTIVE — local / prod
- SERVER_PORT — backend port (default 8080)

Frontend (.env.local)
- REACT_APP_API_BASE_URL — URL where backend APIs are reachable (e.g., http://localhost:8080/api)
- REACT_APP_OPENAI_KEY — (Only if frontend uses the key directly; recommended: proxy via backend)

Important security note: never commit secrets to the repository. Keep keys in environment variables or secret stores.

## Local Setup (Backend & Frontend)

### Backend (Spring AI)
1. cd backend
2. Install dependencies and build:
   - mvn clean package
3. Configure environment variables or edit `src/main/resources/application.yaml` (or externalize via `.env`)
4. Run:
   - mvn spring-boot:run
   or
   - java -jar target/*.jar
5. Backend will start on the configured `SERVER_PORT` (default 8080).

If you use a vector DB or embeddings service, ensure it is reachable before starting the backend so that RAG indexing and retrieval function correctly.

### Frontend (React)
1. cd frontend
2. Install dependencies:
   - npm install
   or
   - yarn
3. Start dev server:
   - npm start
4. Open http://localhost:3000 (or configured port) and ensure `REACT_APP_API_BASE_URL` points to your backend.

To build for production:
- npm run build
- Deploy `build/` static files to your preferred static host or serve them via the backend.

## Using the Chatbot & RAG
- The chatbot endpoints typically accept a conversation payload and return messages backed by generative AI and retrieval from the vector store.
- Before using RAG features:
  1. Ensure product documents or knowledge base entries are indexed into the vector store (check `backend` for indexing scripts or a `/api/ingest` endpoint).
  2. Confirm embedding model and vector storage configuration in env vars.

Typical flow:
1. Ingest product docs -> create embeddings -> store vectors
2. User query -> retrieve relevant docs -> compose prompt with context -> call LLM -> return chat reply

## API Examples

Chat (example)
curl:
