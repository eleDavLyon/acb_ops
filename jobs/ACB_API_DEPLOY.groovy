import utilities.AcbUtils

// Job de déplpoiement de l'application sur un environnement cible (Dev, Recette, Pré-production, Production)
def job = freeStyleJob('ACB_API_DEPLOY'){

    // Description du job.
    description('Ce job permet de déployer une version (Snapshot ou Release) de l\'application sur un environnement cible (Dev, Recette, Pré-production, Production)')


//    Définir les paramètres du Job
    parameters {
        booleanParam('debug', true, 'Exécuter le job en mode Debug.')
        choiceParam('environment', ['dev', 'qlf', 'pprd', 'prod'], 'Environnement cible de déploiement.')
        choiceParam('repository', ['snapshots', 'releases'], 'Repository (Snapshots/Releases) sur lequel seront téléchargés les livrables')
        stringParam('branch', 'master', 'Branche à utiliser pour effectuer le deploiement')
        stringParam('app_acb_version', '1.0.0-SNAPSHOT', 'Version de l\'application à déployer.')
        booleanParam('installComplete', false, 'Installation de tous les rôles du playbook.')
        booleanParam('firewall', false, 'Installation du par-feu de sécurité avec les règles de filtrage iptables.')
        booleanParam('postgres', false, 'Installation du serveur de base de données PostgreSQL et du schéma.')
        booleanParam('openjdk8', false, 'Installation du Java Development Kit (JDK 1.8).')
        booleanParam('app_acb', false, 'Installation de l\'application SpringBoot - Batch.')
    }

    //    Récupérer sur Git la branche à utiliser pour faire le deploiement
    scm {
        git {
            remote {
                url('https://github.com/eleDavLyon/acb_ops.git')
            }
            branch('${branch}')
            extensions{
                localBranch('${branch}')
            }
        }
    }
    steps {
        shell(readFileFromWorkspace('scripts/ACB_API_DEPLOY/deploy.sh'))
    }
//    TODO Envoyer un mail de notification à la fin du deploiement
}
AcbUtils.defaultWrappersPolicy(job)
AcbUtils.defaultLogRotatorPolicy(job)

// À activer avec parcimonie. Il arrive parfois qu'on ait besoin de regarder les fichiers du workspace pour comprendre une erreur; un echec.
// AcbUtils.defaultPublishers(job)
