package com.skillsoft.mocks;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeeklyReportsBatchJobTest {

    @Mock
    private EmailSender emailSenderMock;

    @Mock
    private ResourceProvisioner resourceProvisionerMock;

    @Mock
    private DataPreparer dataPreparerMock;

    @InjectMocks
    WeeklyReportsBatchJob batchJob;

//    private AutoCloseable closeable;

//    @BeforeEach
//    public void setupMocks() {
//        closeable = openMocks(this);
//    }

//    @AfterEach
//    public void releaseMocks() throws Exception {
//        closeable.close();
//    }

//    @Test
//    public void testInitialize() {
//        batchJob.initialize();
//
//        verify(resourceProvisionerMock).initialize();
//        verify(dataPreparerMock).initialize();
////        verifyNoInteractions(dataPreparerMock);
//
//        verifyNoInteractions(emailSenderMock);
//    }

    @Test
    public void testGenerateWeeklyReport() {
//        WeeklyReportsBatchJob batchJob = new WeeklyReportsBatchJob(emailSenderMock);
//        when(resourceProvisionerMock.setupCluster(
//                4, "Linux", "Spark")).thenReturn(true);
//
//        when(dataPreparerMock.prepareRawData()).thenReturn(true);

        when(resourceProvisionerMock.setupSingleMachine("Linux", "Spark"))
                .thenReturn(true).thenReturn(false);

        when(emailSenderMock.sendEmail("john@infomoto.com",
                "The Sales weekly report has been generated")).thenReturn(true);

//        batchJob.initialize();
        batchJob.initializeReportGenerationConfig(4, "Linux", "Spark");
        batchJob.generateWeeklyReport("Sales", "john@infomoto.com");

//        verify(resourceProvisionerMock, times(4))
//                .setupSingleMachine("Linux", "Spark");
//        verify(resourceProvisionerMock, atLeastOnce())
//                .setupSingleMachine("Linux", "Spark");
        verify(resourceProvisionerMock, atLeast(2))
                .setupSingleMachine("Linux", "Spark");
        verify(resourceProvisionerMock, atMost(4))
                .setupSingleMachine("Linux", "Spark");
        verify(emailSenderMock, only())
                .sendEmail("john@infomoto.com", "The Sales weekly report has been generated");
        verify(emailSenderMock, never())
                .sendEmailWithAttachment("john@infomoto.com",
                        "The Sales weekly report has been generated", "someString".getBytes());

//        InOrder inOrderResourceProvisionerMock = inOrder(resourceProvisionerMock);
//        inOrderResourceProvisionerMock.verify(resourceProvisionerMock).initialize();
//        inOrderResourceProvisionerMock.verify(resourceProvisionerMock)
//                        .setupCluster(anyInt(), anyString(), anyString());
//
//        verifyNoMoreInteractions(resourceProvisionerMock);
//
//        InOrder inOrderDataPreparerMock = inOrder(dataPreparerMock);
//        inOrderDataPreparerMock.verify(dataPreparerMock).initialize();
//        inOrderDataPreparerMock.verify(dataPreparerMock).prepareRawData();
//
//        verifyNoMoreInteractions(dataPreparerMock);
//
//        verify(emailSenderMock).sendEmail("john@infomoto.com",
//                "The Sales weekly report has been generated");
//
//        verifyNoMoreInteractions(emailSenderMock);

//        verifyNoInteractions(resourceProvisionerMock);

//        verify(resourceProvisionerMock).initialize();
//        verify(resourceProvisionerMock)
//                .setupCluster(4, "Linux", "Spark");

//        verify(dataPreparerMock).initialize();
//        verify(dataPreparerMock).prepareRawData();

//        assertTrue(batchJob.generateWeeklyReport("Sales", "john@infomoto.com"));
    }

//    @Test
//    public void testGenerateWeeklyReport_multipleRecipients() {
////        WeeklyReportsBatchJob batchJob = new WeeklyReportsBatchJob(emailSenderMock);
//
//        when(emailSenderMock.sendEmailToMultipleRecipients(
//                new String[] {"john@infomoto.com", "julia@infomoto.com"},
//                "The Revenues weekly report has been generated")).thenReturn(true);
//
//        assertTrue(batchJob.generateWeeklyReport(
//                "Revenues", new String[] {"john@infomoto.com", "julia@infomoto.com"}));
//    }

    @Test
    public void sendWeeklyReport_withAttachment() {
//        WeeklyReportsBatchJob batchJob = new WeeklyReportsBatchJob(emailSenderMock);
        when(resourceProvisionerMock.setupSingleMachine(
                "Linux", "Java")).thenReturn(true);

        when(dataPreparerMock.prepareRawData()).thenReturn(true);

        when(emailSenderMock.sendEmailWithAttachment(
                "john@infomoto.com",
                "The Profits weekly report has been generated",
                "Sales have been going up!".getBytes())).thenReturn(true);

//        batchJob.initialize();
        batchJob.initializeReportSendingConfig("Linux", "Java");
        batchJob.sendWeeklyReport("Profits", "john@infomoto.com");

        InOrder inOrderResourceProvisionerMock = inOrder(resourceProvisionerMock);
        inOrderResourceProvisionerMock.verify(resourceProvisionerMock).initialize();
        inOrderResourceProvisionerMock.verify(resourceProvisionerMock)
                        .setupSingleMachine(anyString(), anyString());

        verifyNoMoreInteractions(resourceProvisionerMock);

        InOrder inOrderDataPreparerMock = inOrder(dataPreparerMock);
        inOrderDataPreparerMock.verify(dataPreparerMock).initialize();
        inOrderDataPreparerMock.verify(dataPreparerMock).prepareRawData();

        verifyNoMoreInteractions(dataPreparerMock);

        verify(emailSenderMock).sendEmailWithAttachment(
                "john@infomoto.com",
                "The Profits weekly report has been generated",
                "Sales have been going up!".getBytes());

        verifyNoMoreInteractions(emailSenderMock);

//        verify(resourceProvisionerMock).initialize();
//        verify(resourceProvisionerMock).setupSingleMachine("Linux", "Java");
//
//        verify(dataPreparerMock).initialize();
//        verify(dataPreparerMock).prepareRawData();

//        assertTrue(batchJob.sendWeeklyReport(
//                "Profits", "john@infomoto.com"));
    }

//    @Test
//    public void sendWeeklyReport_withAttachment() {
//        WeeklyReportBatchJob batchJob = new WeeklyReportBatchJob(emailSenderMock);
//
//        when(emailSenderMock.sendEmailWithAttachment(
//                "john@infomoto.com",
//                "The Profits weekly report has been generated",
//                "Sales have been going up!".getBytes())).thenReturn(true);
//
//        assertTrue(batchJob.sendWeeklyReport(
//                "Revenues", "john@infomoto.com"));
//    }
}
