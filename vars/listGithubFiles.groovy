import org.kohsuke.github.*
import com.cloudbees.hudson.plugins.folder.*
import jenkins.model.Jenkins


def genViewFolder(jobPath)
{

    def folders = jobPath.split('/');
    Jenkins jenkins = Jenkins.instance // saves some typing
    def folder = null

    folders.eachWithIndex { folderName, idx -> ​​​​​​​​​​​​​​
        println("Folder: "+folders[idx])

        if(idx == 0) 
            { 
                folder = jenkins.getItem(folders[idx]);         
                if (folder == null) 
                {
                
                    print("Exist : NO  (trying to create '"+folders[idx]+"') ")
                    jenkins.createProject(Folder.class, folders[idx])
                    folder = jenkins.getItem(folders[idx])                    
                            
                  
                }
                else
                {
                    println("Exist : YES (skipping creation '"+folders[idx]+"')")
                    folder = folder.getItem(folders[idx])
                }
            }
        else
            {
                folder = folder.getItem(folders[idx]);         
                if (folder == null) 
                {
                
                    print("Exist : NO  (trying to create '"+folders[idx]+"') ")
                    jenkins.createProject(Folder.class, folders[idx])
                    folder = folder.getItem(folders[idx])                    
                            
                  
                }
                else
                {
                    println("Exist : YES (skipping creation '"+folders[idx]+"')")
                    folder = folder.getItem(folders[idx])
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
