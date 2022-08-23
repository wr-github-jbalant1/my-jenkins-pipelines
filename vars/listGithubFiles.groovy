import org.kohsuke.github.*
import com.cloudbees.hudson.plugins.folder.*
import jenkins.model.Jenkins


def genViewFolder(jobPath)
{

    def folders = jobPath.split('/');
    Jenkins jenkins = Jenkins.instance // saves some typing
    def folder = null

    folders.eachWithIndex { folderName, idx ->
        println("Folder: "+folders[idx])

        if(idx == 0) 
            { 
                         
                if (jenkins.getItem(folders[idx]) == null) 
                    {
                    
                        print("    Exist : NO  (trying to create '"+folders[idx]+"') ")
                        folder = jenkins.createProject(Folder.class, folders[idx])
                    }
                else
                    {
                        println("    Exist : YES (skipping creation '"+folders[idx]+"')")                    
                    }
            }
        else
            {
                if ( folder.getItem(folders[idx]) == null ) 
                    {
                    
                        print("    Exist : NO  (trying to create '"+folders[idx]+"') ")
                        folder = folder.createProject(Folder.class, folders[idx])                  
                    }
                else
                    {
                        println("    Exist : YES (skipping creation '"+folders[idx]+"')")
                    }
            }

    }

}

def call() {
   
    List dslScripts = []
    def myViewPath = ''

    node('master') {

        stage('Scan') {
            println("Search repos for DSL scripts ...")

        }

        stage('Create') {
            println("Create jobs from DSL scripts ...")
            myViewPath = 'SALAMA/pikantna'
            println("Creating view folder: "+myViewPath)
            genViewFolder(myViewPath)
            myViewPath = 'HRENOVKA/piscancja/pikantna'
            println("Creating view folder: "+myViewPath)
            genViewFolder(myViewPath)
            

        }
    }
}
