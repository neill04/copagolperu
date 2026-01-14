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
                    sh 'nohup java -jar target/copagolperu-0.0.1-SNAPSHOT.jar --server.port=8082 > app.log 2>&1 &'
                    sh 'sleep 30'

                    try {
                        sh 'newman run postman/copagolperu_test.json'
                    } finally {
                        sh 'pkill -f copagolperu'
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