pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11'
            args '-u root:root'
        }
    }
    stages {
        stage('Run JMeter Tests') {
            steps {
                sh 'mvn clean install -DskipTests'
                sh 'mvn jmeter:configure'
                sh 'mvn compile'
                // sh 'mvn jmeter:jmeter'
            }
        }
    }
}
