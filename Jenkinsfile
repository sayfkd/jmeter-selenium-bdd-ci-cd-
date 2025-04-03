pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11'
            args '-u root:root'
        }
    }
    environment {
        JMETER_RESULTS_DIR = "target/jmeter/results"
        JMETER_REPORTS_DIR = "target/jmeter/reports"
    }
    stages {
        stage('Run JMeter Tests') {
            steps {
                sh 'mvn clean install -DskipTests'
                sh 'mvn jmeter:configure'
                sh 'mvn compile'
                sh 'mvn jmeter:jmeter -Djmeter.results.directory=${JMETER_RESULTS_DIR}'
            }
        }
        stage('Publish JMeter Reports') {
            steps {
                // Analyse les résultats de JMeter et génère des rapports
                publishHTML (target: [
                    reportName: 'JMeter Report',
                    reportDir: "${JMETER_REPORTS_DIR}",
                    reportFiles: 'index.html',
                    keepAll: true
                ])

                // Utilisation du plugin Performance pour afficher les résultats sous forme de graphique
                recordIssues(tools: [jmeter(resultsFile: "${JMETER_RESULTS_DIR}/test.jtl")])
            }
        }
    }
      post {
        always {
            // Clean up après l'exécution du pipeline
            cleanWs()
        }
    }
}
