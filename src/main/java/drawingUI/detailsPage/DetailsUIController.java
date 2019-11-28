package drawingUI.detailsPage;

import drawingUI.logPage.logtypePanel;

import javax.swing.*;

public class DetailsUIController
{
    personalTab ptab = new personalTab();
    diabetesTab dtab = new diabetesTab();
    doctorTab doctab = new doctorTab();
    detailsPanel dpanel = new detailsPanel(ptab, dtab, doctab);

    buttonPanel butpanel = new buttonPanel();

    logtypePanel lpanel = new logtypePanel();

    public DetailsUIController(JFrame frame)
    {
        frame.getContentPane().add(new enter_details_Panel(dpanel, butpanel, lpanel));
    }


}
