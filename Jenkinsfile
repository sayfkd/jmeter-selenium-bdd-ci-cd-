pipeline {
    agent {
        docker {
            image 'alpine/jmeter'
            args '-v $PWD:/tests' // pour monter ton répertoire si besoin
        }
    }

    environment {
        JMETER_TEST_FILE = "src/test/jmeter/SQL.jmx"
        REPORT_DIR = "jmeter-report"
    }

    stages {
        stage("Préparation") {
            steps {
                echo "📦 Nettoyage du projet"
                sh "mkdir -p ${REPORT_DIR}"
            }
        }

       stage("Run JMeter Tests") {
    steps {
        echo "🚀 Vérification du fichier JMX et exécution des tests JMeter..."

        // Affiche le contenu du dossier pour debug
        sh "echo '📂 Contenu de test/jmeter :' && ls -l src/test"

        // Vérifie si le fichier existe, sinon erreur explicite
        // sh """
        //     if [ ! -f ${JMETER_TEST_FILE} ]; then
        //         echo '❌ Le fichier ${JMETER_TEST_FILE} est introuvable !'
        //         exit 1
        //     fi
        // """
        sh "mvn clean verify"
        // Test de la version JMeter (sanity check)
        sh "echo '✅ JMeter version :' && jmeter -v"

        // Lancement du test JMeter
        sh """
            jmeter -n -t ${JMETER_TEST_FILE}
        """
    }
}


        stage("Archive Report") {
            steps {
                echo "🗂 Archivage du rapport JMeter..."
                archiveArtifacts artifacts: "${REPORT_DIR}/**", fingerprint: true
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: "${REPORT_DIR}/html",
                    reportFiles: 'index.html',
                    reportName: "JMeter Report"
                ])
            }
        }
    }

    post {
        always {
            echo "✅ Pipeline terminée"
        }
        success {
            echo "🎉 Tests JMeter exécutés avec succès"
        }
        failure {
            echo "❌ Échec lors de l'exécution des tests JMeter"
        }
    }
}
