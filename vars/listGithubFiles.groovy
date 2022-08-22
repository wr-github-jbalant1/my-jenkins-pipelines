import org.kohsuke.github.*
import com.cloudbees.hudson.plugins.folder.*
import jenkins.model.Jenkins


def genViewFolder(folderName)
{
    Jenkins jenkins = Jenkins.instance // saves some typing
    def folder = jenkins.getItem(folderName)
        if (folder == null) {
            // Create the folder if it doesn't exist or if no existing job has the same name
            folder = jenkins.createProject(Folder.class, folderName)
            }

}

def call() {
   
    List dslScripts = []
    //String folderName = "KLOBASA"

    node('master') {

        stage('Scan') {
            println("Search repos for DSL scripts ...")

        }

        stage('Create') {
            println("Create jobs from DSL scripts ...")
            genViewFolder("KLOBASA/Kuhana")
            //genViewFolder("SALAMA/SUHA")
            

        }
    }
}
