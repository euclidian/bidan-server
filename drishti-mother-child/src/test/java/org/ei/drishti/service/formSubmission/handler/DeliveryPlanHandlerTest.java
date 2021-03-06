package org.ei.drishti.service.formSubmission.handler;

import org.ei.drishti.form.domain.FormSubmission;
import org.ei.drishti.service.ANCService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeliveryPlanHandlerTest {
    @Mock
    private ANCService ancService;

    private DeliveryPlanHandler handler;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        handler = new DeliveryPlanHandler(ancService);
    }

    @Test
    public void shouldDelegateFormSubmissionHandlingToBothANCAndPNCService() throws Exception {
        FormSubmission submission = new FormSubmission("anm id 1", "instance id 1", "delivery_plan", "entity id 1", 0L, "1", null, 0L);

        handler.handle(submission);

        verify(ancService).deliveryPlanned(submission);
    }
}
