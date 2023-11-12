package tn.esprit.devops_project.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.*;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;


import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;
    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private OperatorRepository operatorRepository;

    private Operator operator;


    @Test
    public void itShouldRetrieveAllInvoices() {
        // Arrange
        List<Invoice> expectedInvoices = Arrays.asList(
                new Invoice()
        );
        when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        // Act
        List<Invoice> allInvoices = invoiceService.retrieveAllInvoices();

        // Assert
        Assertions.assertThat(allInvoices).isEqualTo(expectedInvoices);
    }

    @Test
    public void itShouldCancelInvoice() {


        Supplier supplier = Supplier.builder()
                .idSupplier(1L)
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .label("Sample Supplier")
                .code("S123")
                .build();


        Long invoiceId = 1L;
        Invoice expectedInvoice =  Invoice.builder()
                .idInvoice(1L)
                .amountDiscount(100.0f)
                .amountInvoice(500.0f)
                .dateCreationInvoice(new Date())
                .dateLastModificationInvoice(new Date())
                .archived(false)
                .invoiceDetails(new HashSet<>())
                .supplier(supplier)
                .build();


        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));
        invoiceService.cancelInvoice(invoiceId);
        verify(invoiceRepository).save(expectedInvoice);


    }

    @Test
    public void itShouldRetrieveInvoice() {

        Supplier supplier = Supplier.builder()
                .idSupplier(1L)
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .label("Sample Supplier")
                .code("S123")
                .build();

        // Arrange
        Long invoiceId = 1L; // Replace with a valid invoice ID
        Invoice expectedInvoice =  Invoice.builder()
                .idInvoice(1L)
                .amountDiscount(100.0f)
                .amountInvoice(500.0f)
                .dateCreationInvoice(new Date())
                .dateLastModificationInvoice(new Date())
                .archived(false)
                .invoiceDetails(new HashSet<>())
                .supplier(supplier)
                .build();


        // Mock the behavior of the repository's findById method
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // Act
        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Assert
        Assertions.assertThat(retrievedInvoice).isEqualTo(expectedInvoice);


    }

    @Test
    public void itShouldAssignOperatorToInvoice() {

        Long idOperator = 1L;
        Long idInvoice = 1L;

        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(idInvoice)).thenReturn(Optional.of(invoice));

        Set<Invoice> invoices = new HashSet<>();
        Operator operator = new Operator(1L,"John", "Doe", "password123", invoices);

        when(operatorRepository.findById(idOperator)).thenReturn(Optional.of(operator));

        invoiceService.assignOperatorToInvoice(idOperator, idInvoice);

        Assertions.assertThat(operator.getInvoices()).contains(invoice);
    }

    @Test
    public void itShouldGetTotalAmountInvoiceBetweenDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();

        float expectedTotalAmount = 1000.0f;

        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedTotalAmount);


        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);


        Assertions.assertThat(totalAmount).isEqualTo(expectedTotalAmount);
    }







}
