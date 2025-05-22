package br.com.fiap.fiapsoftteck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.fiapsoftteck.database.AppDatabase
import br.com.fiap.fiapsoftteck.repository.DiarioHumorRepository
import br.com.fiap.fiapsoftteck.repository.UsuarioRepository
import br.com.fiap.fiapsoftteck.ui.theme.FiapSoftteckTheme
import br.com.fiap.fiapsoftteck.view.ShowPsychosocialQuestionnaire
import br.com.fiap.fiapsoftteck.view.ShowSupportLinks
import br.com.fiap.fiapsoftteck.view.SplashTela
import br.com.fiap.fiapsoftteck.view.TelaCadastro
import br.com.fiap.fiapsoftteck.view.TelaDiarioHumor
import br.com.fiap.fiapsoftteck.view.TelaHome
import br.com.fiap.fiapsoftteck.view.TelaLogin
import br.com.fiap.fiapsoftteck.view.TelaLoginWrapper
import br.com.fiap.fiapsoftteck.viewmodel.DiarioHumorViewModel
import br.com.fiap.fiapsoftteck.viewmodel.QuestionViewModel
import br.com.fiap.fiapsoftteck.viewmodel.UsuarioViewModel

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)

        val usuarioRepository = UsuarioRepository(database.usuarioDao())

        

        val viewModel = UsuarioViewModel(usuarioRepository)
        enableEdgeToEdge()
        setContent {
            FiapSoftteckTheme {
                var mostrarSplash by remember { mutableStateOf(true) }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (mostrarSplash){
                        SplashTela {
                            mostrarSplash = false
                        }
                    }else{
                        TelaLoginWrapper()
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: UsuarioViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = "login"
    )
    {
        composable("login") {
            TelaLogin(
                viewModel = viewModel,
                onNavigateToCadastro = { navController.navigate("cadastro") },
                onLoginSucesso = { navController.navigate("home") })
        }
        composable("cadastro") {
            TelaCadastro(
                viewModel = viewModel,
                onCadastroConcluido = { navController.navigate("home") })
        }
        composable("home") {
            TelaHome(
                viewModel = viewModel,
                onNavigateToHumor = { navController.navigate("humor") },
                navController = navController
            )
        }

        composable("humor") {
            val context = LocalContext.current
            val db = remember { AppDatabase.getDatabase(context) }
            val humorViewModel = remember {
                DiarioHumorViewModel(DiarioHumorRepository(db.diarioHumorDao()))
            }
            viewModel.usuarioLogado?.let { usuario ->
                TelaDiarioHumor(
                    usuarioId = usuario.id,
                    viewModel = humorViewModel,
                    navController = navController)
            }
        }

        composable("apoio") {
            ShowSupportLinks(navController = navController)
        }

        composable("questionario") {
            val questionViewModel = viewModel<QuestionViewModel>()

            ShowPsychosocialQuestionnaire(
                usuarioId = viewModel.usuarioLogado?.id ?: 0,
                viewModel = questionViewModel,
                navController = navController
            )}
    }

}



