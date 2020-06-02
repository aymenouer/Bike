/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

/**
 *
 * @author aymen
 */
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import models.Abonnement;
import services.Abonnment_Service;

/**
 *
 * @author shannah
 */
class StatAbonnment {
    Resources theme ; 

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(50);

        renderer.setLegendTextSize(50);
        renderer.setMargins(new int[]{20, 30, 15, 0});

        renderer.setLabelsColor(ColorUtil.BLACK);

        for (int color : colors) {

            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, ArrayList<Abonnement> ab) {
        CategorySeries series = new CategorySeries(title);

        for (Abonnement u : ab) {
            series.add(u.getLib_C(), u.getQuantite());

        }

        return series;
    }

    public void createPieChartForm(String titre, ArrayList<Abonnement> ab) {
        // Generate the values
        
        theme = UIManager.initFirstTheme("/theme");
        int size = ab.size();
        double[] values = new double[size];
        int i = 0;
        for (Abonnement u : ab) {
            values[i] = u.getQuantite();
            i++;
        }

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.rgb(255,102,1), ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN, ColorUtil.YELLOW};

        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset(titre, ab), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form(new BorderLayout());
        Label logi = new Label("STATISTIQUES");
        logi.setUIID("login");
     
        f.add(BorderLayout.NORTH, logi);
        f.add(BorderLayout.CENTER, c);
         f.getToolbar().setUIID("tb");
         
        f.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt)
                -> {
            new addAbonnmentForm(f).showBack();
        });
        f.show();

    }
}
