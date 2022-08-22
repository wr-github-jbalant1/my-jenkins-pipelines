import org.kohsuke.github.*
import com.cloudbees.hudson.plugins.folder.*
import jenkins.model.Jenkins

def call() {
   
    List dslScripts = []
    Jenkins jenkins = Jenkins.instance // saves some typing
    String folderName = "KLOBASA"

    node('master') {

        stage('Scan') {
            println("Search repos for DSL scripts ...")

        }

        stage('Create') {
            println("Create jobs from DSL scripts ...")
            def folder = jenkins.getItem(folderName)
            if (folder == null) {
            // Create the folder if it doesn't exist or if no existing job has the same name
            folder = jenkins.createProject(Folder.class, folderName)
            }
        }
    }
}
