import utilities.AcbUtils
// Job d'exécution des tests Junit et transfert des résultats à Sonar
def job = mavenJob('ACB_API_SONAR_BRANCH'){

    // Description du job.
    description('Ce job permet de lancer un scan sonar sur une branche de  l\'application Alerte Compte Bancaire')

//    Exécution automatique du job tous les jours à 6h et à 13H
    triggers {
        cron("H 6,13 * * *")
    }


//    Définir les paramètres du Job
    parameters {
        stringParam('branch', 'master', 'Branche à utiliser pour lancer un scan sonar')
    }

//    Récupérer sur Git la branche à utiliser pour faire le snapshot
    scm {
        git {
            remote {
                url('https://github.com/eleDavLyon/acb_api.git')
            }
            branch('${branch}')
        }
    }

    goals('clean verify sonar:sonar -Dsonar.host.url=http://nexus.davidson.fr:9000')
//    TODO Envoyer un mail de notification à la fin du build sonar
}
AcbUtils.defaultWrappersPolicy(job)
AcbUtils.defaultLogRotatorPolicy(job)