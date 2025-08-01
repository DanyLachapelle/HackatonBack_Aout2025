package school.token.groupe7_hackatonback_aout2025.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.token.groupe7_hackatonback_aout2025.service.SystemFolderService;

/**
 * Configuration pour initialiser les dossiers système au démarrage de l'application
 */
@Configuration
public class SystemFolderInitializer {

    /**
     * Initialise les dossiers système pour tous les utilisateurs au démarrage
     */
    @Bean
    public ApplicationRunner initSystemFolders(SystemFolderService systemFolderService) {
        return args -> {
            try {
                System.out.println("🗂️ Initialisation des dossiers système...");
                systemFolderService.initializeSystemFoldersForAllUsers();
                System.out.println("✅ Dossiers système initialisés avec succès");
            } catch (Exception e) {
                System.err.println("❌ Erreur lors de l'initialisation des dossiers système: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}