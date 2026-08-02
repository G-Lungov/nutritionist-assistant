 ## Nutren - Standalone Meal Plan Generator (MVP)
Nutren is a high-utility, lightning-fast Command Line Interface (CLI) application built in Java 21 to automate the creation of personalized medical and nutritional meal plans.
The application runs entirely locally without requiring a Microsoft Office installation or external web servers. It parses custom template data from an XML payload, maps those fields dynamically, injects them into a Microsoft Word DOCX file template, and outputs a pixel-perfect, ready-to-print PDF document.

## 🌟 Features
* 100% Native Code Execution: No Microsoft Word or LibreOffice runtime requirements on the host machine.
* Dynamic Data Mapping: Built utilizing generic key-value maps. You can add new fields to your XML file and Word template at any time without changing a single line of Java source code.
* Interactive Terminal Session: Features an active terminal loop allowing users to process multiple plans sequentially without closing the prompt.
* Docker-Isolated Workspace: Ship-ready development workspace using VS Code Dev Containers, guaranteeing zero installation conflicts with local machine tools.

## 🛠️ Technology Stack & Dependencies
The project follows a streamlined N-Tier (Layered Architecture) variant adapted for specialized file-processing CLI environments, maintaining decoupling between I/O inputs, XML parsing services, and document rendering pipelines.
### Core Stack
* Language: Java 21 (Eclipse Temurin JDK distribution).
* Build Automation & Dependency Manager: Apache Maven 3.9+.
* Containerization & Sandbox: Docker & Docker Compose.
* Integrated Development Environment: VS Code with the Dev Containers extension ecosystem.
### Frameworks & Libraries (POM Artifacts)
* [Apache POI (v5.2.5)](https://poi.apache.org/) – Used to deep-scan, manipulate, and rebuild Open Office XML format structures (.docx) programmatically, processing text paragraphs and structural tables.
* [XDocReport PDF Converter (v2.0.4)](https://github.com/opensagres/xdocreport) – Translates the styling metadata of the dynamically altered Word document directly into an independent PDF byte stream using a headless OpenPDF backend.
* [Jackson Dataformat XML (v2.17.0)](https://github.com/FasterXML/jackson-dataformat-xml) – Handles stream-parsing of standard hierarchical .xml files, unmarshalling tags cleanly into a generic Java Map<String, String>.
* [Apache Log4j Core (v2.20.0)] – Enforced explicit logging classpath dependencies to prevent shading collisions during Fat JAR assembly.

## 🚀 Getting Started - Prerequisites
Make sure your physical host computer has the following tools installed and running:

   1. Docker Desktop (With WSL2 integration active if on Windows).
   2. Visual Studio Code.
   3. Dev Containers extension (Published by Microsoft).
### 1. Mounting the Sandbox Environment
   1. Clone this repository onto your machine `git clone github.com/G-Lungov/nutritionist-assistant`.
   2. Open VS Code.
   3. Open the project root folder pressing `Ctrl + K + O`.
   4. Press `Ctrl + Shift + P` (or `Cmd + Shift + P` on Mac) to bring up the command palette.
   5. Type and select: Dev Containers: Reopen in Container.
   6. Docker will assemble the image, mount your workspace inside /workspace, install Java 21 support extensions, and open a Linux bash terminal within the sandboxed environment.
### 2. Building the Executable Artifact
To package all source layers, components, and third-party dependencies into a single, redistributable Fat JAR, run the following command inside your Dev Container terminal:

`mvn clean package`

This builds and drops the compiled executable artifact directly inside the ./target workspace folder.

## 💡 How to Use (Step-by-Step Example)
### Step A: Preparing Input Templates
1. Inside XML file "example.xml", create a clear key-value element sequence.

```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <data>
      <CLIENT_NAME>John Doe</CLIENT_NAME>
      <DOCUMENT_ID>123.456.789-00</DOCUMENT_ID>
      <CALORIES_GOAL>2400 kcal</CALORIES_GOAL>
    </data>
```

2. Inside Word file "example.docx": Write your regular layout text inside MS Word or any editor. Place template markers matching your XML tags wrapped within ${...} blocks exactly like this:

```xml
" Meal Plan developed for ${CLIENT_NAME}
  ID: ${DOCUMENT_ID}
  kcal goal: ${CALORIES_GOAL}. "
```

### Step B: Execution
Launch the runner artifact using the JVM archive flag:

`java -jar target/nutren-1.0.0-SNAPSHOT.jar`

Follow the interactive prompts using complete paths like this example:
```bash
D:/User/Documents/doc_template.docx (enter template path, including file name and extension)

D:/User/Documents/xml_data.xml (enter template path, including file name and extension)

D:/User/Documents/pdf_example.pdf (enter final file path and name it as you want)
```
Your fresh "example.pdf" will be generated on disk instantly, viewable right inside your local machine's folder.


## Credits
This project was designed and developed by:

* **Gabriel Lungov** - *Lead Developer* - [GitHub Profile](https://github.com/G-Lungov)
