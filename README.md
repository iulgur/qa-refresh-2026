# QA Refresh 2026 – Playwright Test Automation

Personal project to refresh QA automation skills, built as part of an
8-week hands-on learning plan covering Playwright, API testing, databases,
CI/CD, and AI-assisted testing.

## Description

This repository contains an automated test suite built with Playwright
and Java, as part of a structured self-study program aimed at refreshing
and modernizing QA automation skills.

## Tech Stack

- Java 21
- Maven
- Playwright

## Prerequisites

- Java 21 installed
- Maven installed
- IntelliJ IDEA (recommended)

## Running the Tests

Clone the repository and run tests via Maven:

\`\`\`bash
mvn test
\`\`\`

Or run an individual test class from the command line:

\`\`\`bash
mvn test -Dtest=FirstPlaywrightTest
\`\`\`

Tests can also be run directly from IntelliJ IDEA by clicking the green
run icon next to a test method or class.

By default, tests run in headless mode. To run with a visible browser,
launch options can be configured with `setHeadless(false)`.

## Progress

- **Week 0** — Environment setup (Java 21, Maven, Playwright, JUnit 5),
  first passing Playwright test, CI-ready project structure.
- 