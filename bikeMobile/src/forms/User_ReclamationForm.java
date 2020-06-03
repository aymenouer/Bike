/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import ca.weblite.codename1.components.ckeditor.CKeditor;
import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import models.reclamation;
import services.Reclamation_Service;

/**
 *
 * @author aymen
 */
public class User_ReclamationForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");

    public User_ReclamationForm(Form previous) throws Exception {
        super(BoxLayout.y());
        Label logi = new Label("MES RECLAMATIONS");
        logi.setUIID("login");
        this.add(logi);

        for (reclamation c : new Reclamation_Service().getAllReclamationsUser()) {

            this.add(addItem_reclamation(c));

        }
                this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt)
                -> {
            previous.show();
        });
        this.getToolbar().addCommandToOverflowMenu("Add reclamation", null, ev -> {

            Form ajout = new Form(BoxLayout.y());
            Label logia = new Label("ADD RECLAMATION");
            logia.setUIID("login");
            ajout.add(logia);
            CKeditor editor = new CKeditor();
            ComboBox Type = new ComboBox("Abonnment", "Accessoire", "Piece", "Velo", "Site", "Maintenance", "Panier", "Others");
            Type.setUIID("txtn");
            editor.initLater();
            Label AA = new Label("TYPE");
            AA.setUIID("pass");
            ajout.add(AA).add(Type);
            ajout.getToolbar().setUIID("tb");
            ajout.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
            Label PHOTO = new Label("DRAG YOUR PHOTO HERE");
            PHOTO.setUIID("pass");
            ajout.addComponent(PHOTO);

            if (DropTarget.isSupported()) {

                DropTarget dnd = DropTarget.create((evt) -> {

                    String srcFile = (String) evt.getSource();
                    System.out.println("Src file is " + srcFile);

                    System.out.println("Location: " + evt.getX() + ", " + evt.getY());
                    if (srcFile != null) {
                        try {

                            Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile)).scaled(300, 300);

                            String nomImage = srcFile.substring(19, srcFile.length());

                            ajout.add(img);
                            Container cn_sw = new Container(BoxLayout.x());
                            OnOffSwitch swi = new OnOffSwitch();
                                            // yes
                   String url_photo_like = "http://localhost/bike/web/uploads/images/heart.png" ;
            ImageViewer img_like;
            Image imge_like;
            EncodedImage enc_like;
            enc_like = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge_like = URLImage.createToStorage(enc_like, url_photo_like, url_photo_like);
            img_like = new ImageViewer(imge_like);
                //non
                  String url_photo_deslike = "http://localhost/bike/web/uploads/images/broke_heart.png" ;
            ImageViewer img_deslike;
            Image imge_deslike;
            EncodedImage enc_deslike;
            enc_deslike = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge_deslike = URLImage.createToStorage(enc_deslike, url_photo_deslike, url_photo_deslike);
            img_deslike = new ImageViewer(imge_deslike);
               // 
                            cn_sw.add(imge_deslike).add(swi).add(img_like);
                            ajout.add(cn_sw);
                            ajout.add("Problem : ").add(editor);
                            ajout.revalidate();

                            ajout.getToolbar().addCommandToOverflowMenu("Add reclamation", null, evx -> {

                                String etat = "Mauvais";
                                if (swi.isValue()) {
                                    etat = "Bien";

                                }

                                if (editor.getData().equals("")) {
                                    Dialog.show("erreur", "Problem vide", "OK", null);
                                } else {
                                    String contenu = editor.getData();

                                    contenu = contenu.substring(3);

                                    contenu = contenu.substring(0, contenu.length() - 5);

                                    reclamation r = new reclamation(0, 0, contenu, nomImage, Type.getSelectedItem().toString(), etat, "non");
                                    try {
                                        new Reclamation_Service().addReclamation(r);
                                        Dialog.show("Ajout", "Ajout avec success", "OK", null);
                                        new User_ReclamationForm(previous).showBack();

                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }

                            });

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                    }

                }, Display.GALLERY_IMAGE);
            }

            ajout.show();

        });

    }

    public Container addItem_reclamation(reclamation c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label etat = new Label(c.getEtat());
        Button btn = new Button(c.getType());
        etat.setUIID("libC");
        btn.setUIID("btn");
        if (c.getEtat().equals("Mauvais")) {
            etat.getStyle().setFgColor(0xd61f1f);
        } else {
            etat.getStyle().setFgColor(0x1b7021);
        }

        String url = "http://localhost/bike/web/uploads/images/" + c.getImage();
        ImageViewer imgv;
        Image imge;
        EncodedImage enc;

        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, url, url);
        imgv = new ImageViewer(imge);

        cn2.add(etat).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        cn1.add(BorderLayout.EAST, imgv);

        btn.addActionListener(e -> {

            Form f2 = new Form(BoxLayout.y());
            Label logia = new Label("DETAILS");
            logia.setUIID("login");
            f2.add(logia);
            Label type1 = new Label("TYPE");
            Label type = new Label(c.getType());
            Label type2 = new Label("ETAT");
            Label etat_d = new Label(c.getEtat());
            Label type3 = new Label("CONTENU");
            Label contenu = new Label(c.getContenu());

            type1.setUIID("type1");
            type2.setUIID("type1");
            type3.setUIID("type1");

            type.setUIID("type2");
            etat_d.setUIID("type2");
            contenu.setUIID("type2");
            if (c.getEtat().equals("Mauvais")) {
                etat_d.getStyle().setFgColor(0xd61f1f);
            } else {
                etat.getStyle().setFgColor(0x1b7021);
            }
            String url2 = "http://localhost/bike/web/uploads/images/" + c.getImage();
            ImageViewer imgv2;
            Image imge2;
            EncodedImage enc2;
            enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge2 = URLImage.createToStorage(enc, url, url);
            imgv2 = new ImageViewer(imge2);

            f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), (evt) -> {
                this.showBack();
            });
            f2.add(type1).add(type).add(type2).add(etat_d).add(type3).add(contenu).add(imgv2);

            f2.show();

        });

        cn1.setLeadComponent(btn);
        return cn1;

    }

}
