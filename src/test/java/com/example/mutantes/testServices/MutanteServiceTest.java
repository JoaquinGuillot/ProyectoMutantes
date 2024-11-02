//import com.example.mutantes.entities.Mutante;
//import com.example.mutantes.repositories.MutanteRepository;
//import com.example.mutantes.services.MutanteService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class MutanteServiceTest {
//
//    @InjectMocks
//    private MutanteService mutanteService;
//
//    @Mock
//    private MutanteRepository mutanteRepository;
//
//    private Mutante mutante;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mutante = new Mutante();
//        mutante.setId(1L);
//        mutante.setNombre("Test");
//        mutante.setApellido("Mutante");
//        mutante.setEsMutante(true);
//        mutante.setAdn(new String[]{"A", "A", "A", "A"});
//
//        // Guardar el mutante en el repositorio simulado
//        when(mutanteRepository.findById(1L)).thenReturn(Optional.of(mutante));
//    }
//
//    @Test
//    public void testFindById() throws Exception {
//        Mutante found = mutanteService.findById(1L);
//
//        assertEquals(mutante.getId(), found.getId());
//        assertEquals(mutante.getNombre(), found.getNombre());
//        verify(mutanteRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        Mutante updatedMutante = new Mutante();
//        updatedMutante.setNombre("Updated");
//
//        Mutante result = mutanteService.update(1L, updatedMutante);
//
//        assertEquals("Updated", result.getNombre());
//        verify(mutanteRepository, times(1)).findById(1L);
//        verify(mutanteRepository, times(1)).save(any(Mutante.class));
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        when(mutanteRepository.existsById(1L)).thenReturn(true);
//
//        boolean result = mutanteService.delete(1L);
//
//        assertTrue(result);
//        verify(mutanteRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void testAnalizarADN() {
//        boolean result = mutanteService.analizarADN(mutante);
//        assertTrue(result);
//        verify(mutanteRepository, times(1)).save(mutante);
//    }
//}
