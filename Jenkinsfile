pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    environment {
        SONARQUBE_ENV = 'SonarQube'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Functional Tests (Postman)') {
            steps {
                script {
                    echo 'Iniciando Pruebas Funcionales con Postman'
                    sh 'nohup java -jar target/copagolperu-0.0.1-SNAPSHOT.jar --server.port=8082 --spring.datasource.url=jdbc:h2:mem:testdb --spring.datasource.username=sa --spring.datasource.password= --spring.datasource.driver-class-name=org.h2.Driver --spring.jpa.database-platform=org.hibernate.dialect.H2Dialect > app.log 2>&1 & echo $! > app.pid'
                    sh 'sleep 30'

                    try {
                        sh 'newman run postman/copagolperu_test.json'
                    } catch (Exception e) {
                        sh 'cat app.log'
                        throw e
                    } finally {
                        sh 'kill -9 $(cat app.pid) || true'
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
}