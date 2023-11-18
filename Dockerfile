# Use a imagem base do OpenJDK 17 com Alpine Linux
FROM openjdk:17-jdk-alpine

# Copia todos os arquivos do diretório local para o diretório de trabalho do contêiner
COPY . .

# Copia o Maven Wrapper (mvnw) para o contêiner, se existir
COPY mvnw ./

# Garante que o script mvnw tenha permissões de execução
RUN if [ -f mvnw ]; then chmod +x mvnw; fi

# Executa o comando 'mvn clean install' para realizar o build do projeto
RUN if [ -f mvnw ]; then ./mvnw clean install; else mvn clean install; fi

# Expõe a porta 8080, que é a porta padrão usada pela aplicação
EXPOSE 8080

# Define o comando de entrada para executar a aplicação quando o contêiner for iniciado
ENTRYPOINT ["java", "-jar", "/target/ecommerce-0.0.1.jar"]
