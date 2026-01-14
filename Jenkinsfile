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

        stage('Performance Tests (JMeter)') {
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') {
                        echo 'Iniciando Pruebas de Rendimiento con JMeter'
                        sh 'if [ ! -d "apache-jmeter-5.6.3" ]; then wget https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-5.6.3.tgz && tar -xzf apache-jmeter-5.6.3.tgz; fi'
                        sh 'nohup java -jar target/copagolperu-0.0.1-SNAPSHOT.jar --server.port=8082 --spring.datasource.url=jdbc:h2:mem:testdb --spring.datasource.username=sa --spring.datasource.password= --spring.datasource.driver-class-name=org.h2.Driver --spring.jpa.database-platform=org.hibernate.dialect.H2Dialect > jmeter_app.log 2>&1 & echo $! > jmeter_app.pid'
                        sh 'sleep 30'

                        try {
                            sh './apache-jmeter-5.6.3/bin/jmeter -n -t jmeter/copagolperu_load_test.jmx -l jmeter_results.jtl'
                            echo 'Pruebas de rendimiento finalizadas.'
                        } catch (Exception e) {
                            sh 'cat jmeter_app.log'
                            throw e
                        } finally {
                            sh 'kill -9 $(cat jmeter_app.pid) || true'
                            sh 'rm -rf apache-jmeter-5.6.3.tgz'
                        }
                    }
                }
            }
        }

        stage('Security Tests (OWASP ZAP)') {
            steps {
                script {
                    timeout(time: 15, unit: 'MINUTES') {
                        echo 'Iniciando Pruebas de Seguridad con OWASP ZAP 2.15.0...'
                        sh 'if [ ! -d "ZAP_2.15.0" ]; then wget https://github.com/zaproxy/zaproxy/releases/download/v2.15.0/ZAP_2.15.0_Linux.tar.gz && tar -xzf ZAP_2.15.0_Linux.tar.gz; fi'
                        sh 'nohup java -jar target/copagolperu-0.0.1-SNAPSHOT.jar --server.port=8082 --spring.datasource.url=jdbc:h2:mem:testdb --spring.datasource.username=sa --spring.datasource.password= --spring.datasource.driver-class-name=org.h2.Driver --spring.jpa.database-platform=org.hibernate.dialect.H2Dialect > zap_app.log 2>&1 & echo $! > zap_app.pid'
                        sh 'sleep 40'

                        try {
                            echo '⚡ Ejecutando Escaneo de ZAP...'
                            sh './ZAP_2.15.0/zap.sh -cmd -quickurl http://localhost:8082/api/academias -quickout ${PWD}/zap_report.html -quickprogress'
                            echo 'Escaneo de seguridad finalizado.'
                        } catch (Exception e) {
                            echo 'Error en ZAP. Revisa los logs.'
                            throw e
                        } finally {
                            sh 'kill -9 $(cat zap_app.pid) || true'
                            sh 'rm -f ZAP_2.15.0_Linux.tar.gz'
                            archiveArtifacts artifacts: 'zap_report.html', allowEmptyArchive: true
                        }
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