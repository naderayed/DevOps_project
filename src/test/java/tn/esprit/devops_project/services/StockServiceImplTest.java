package tn.esprit.devops_project.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void itShouldAddStock() {
        // Arrange
        Stock stockToAdd = new Stock(/* provide necessary details */);
        when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd);

        // Act
        Stock addedStock = stockService.addStock(stockToAdd);

        // Assert
        Assertions.assertThat(addedStock).isEqualTo(stockToAdd);
    }


    @Test
    void testAddStock_DuplicateStock() {
        // Arrange
        Stock existingStock = new Stock(/* initialize with valid data */);
        Stock duplicateStock = new Stock(/* initialize with the same identifier as existingStock */);
        when(stockRepository.save(any(Stock.class)))
                .thenThrow(DataIntegrityViolationException.class); // Simulate a duplicate key violation

        // Act and Assert
        assertThrows(DataIntegrityViolationException.class, () -> stockService.addStock(duplicateStock));
        verify(stockRepository, times(1)).save(duplicateStock);
    }

    @Test
    void itShouldRetrieveExistingStock() {
        // Arrange
        Long existingStockId = 1L;
        Stock expectedStock = new Stock();
        expectedStock.setIdStock(existingStockId);
        expectedStock.setTitle("ExistingProduct");

        when(stockRepository.findById(existingStockId)).thenReturn(Optional.of(expectedStock));

        // Act
        Stock retrievedStock = stockService.retrieveStock(existingStockId);

        // Assert
        Assertions.assertThat(retrievedStock).isEqualTo(expectedStock);
    }

    @Test
    void itShouldThrowExceptionForNonExistingStock() {
        // Arrange
        Long nonExistingStockId = 2L;

        when(stockRepository.findById(nonExistingStockId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThatThrownBy(() -> stockService.retrieveStock(nonExistingStockId))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Stock not found");
    }

    @Test
    void itShouldRetrieveAllStock() {
        // Arrange
        Stock stock1 = new Stock();
        stock1.setIdStock(1L);
        stock1.setTitle("Product1");

        Stock stock2 = new Stock();
        stock2.setIdStock(2L);
        stock2.setTitle("Product2");

        List<Stock> expectedStocks = Arrays.asList(stock1, stock2);
        when(stockRepository.findAll()).thenReturn(expectedStocks);

        // Act
        List<Stock> allStocks = stockService.retrieveAllStock();

        // Assert
        Assertions.assertThat(allStocks).isEqualTo(expectedStocks);
    }
    @Test
    void itShouldRetrieveAllStockEmptyList() {
        // Arrange
        List<Stock> expectedStocks = Collections.emptyList();
        when(stockRepository.findAll()).thenReturn(expectedStocks);

        // Act
        List<Stock> allStocks = stockService.retrieveAllStock();

        // Assert
        Assertions.assertThat(allStocks).isEmpty();
    }



}
