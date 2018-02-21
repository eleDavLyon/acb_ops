import utilities.AcbUtils
// Job de déploiement d'une version snapshots de l'application sur le repository Nexus
def job = mavenJob('ACB_API_SNAPSHOT_BRANCH'){

    // Description du job.
    description('Ce job permet de lancer un snapshot sur une branche de  l\'application Alerte Compte Bancaire')

//    Définir les paramètres du Job
    parameters {
        stringParam('branch', 'master', 'Branche à utiliser pour effectuer le snapshot')
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
    goals('clean deploy')
//    TODO Envoyer un mail de notification à la fin du snapshot
}
AcbUtils.defaultWrappersPolicy(job)
AcbUtils.defaultLogRotatorPolicy(job)