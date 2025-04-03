pipeline {
    agent { docker { 
        image 'maven:3.9.6-eclipse-temurin-17' 
        args '--entrypoint=""'
        
        }}  // Utilisation de l'image Maven

    environment {
        DISPLAY = ':99'  // Requis pour exécuter les tests Selenium avec un serveur X virtuel
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://ton-repo.git'  // Remplace avec l'URL de ton repo
            }
        }

        stage('Build & Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests'  // Compile le projet sans exécuter les tests
            }
        }

        stage('Start Selenium Grid') {
            steps {
                sh 'docker-compose up -d'  // Lancer Selenium Grid avec Docker Compose (optionnel)
                sh 'sleep 10'  // Attendre que Selenium Grid démarre
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'mvn test -Dtest=**/*SeleniumTest*'  // Exécuter uniquement les tests Selenium
            }
        }

        stage('Run JMeter Tests') {
            steps {
                sh 'mvn jmeter:jmeter'  // Lancer les tests JMeter avec Maven
            }
        }

        stage('Publish JMeter Report') {
            steps {
                archiveArtifacts artifacts: '**/target/jmeter/results/*.jtl', fingerprint: true
                archiveArtifacts artifacts: '**/target/jmeter/results/*.log', fingerprint: true
            }
        }

        stage('Cleanup') {
            steps {
                sh 'docker-compose down'  // Arrêter Selenium Grid si utilisé
            }
        }
    }
}
