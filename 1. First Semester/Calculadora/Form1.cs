﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApplication19
{
    public partial class Form1 : Form
    {
        double num;
        string operador;

        public Form1()
        {
            InitializeComponent();
            but0.Click +=new EventHandler(num);
            but1.Click += new EventHandler(num);
            but2.Click += new EventHandler(num);
            but3.Click += new EventHandler(num);
            but4.Click += new EventHandler(num);
            but5.Click += new EventHandler(num);
            but6.Click += new EventHandler(num);
            but7.Click += new EventHandler(num);
            but8.Click += new EventHandler(num);
            but9.Click += new EventHandler(num);

            butsoma.Click += new EventHandler(opers);
            butsub.Click += new EventHandler(opers);
            butdiv.Click += new EventHandler(opers);
            butmulti.Click += new EventHandler(opers);
            
        }

     
        private void num(object sender, EventArgs Rec)
        {
            if (label1.Text == "0") { label1.Text = ""; label2.Text = "";}
            label1.Text += ((Button)sender).Text;
            label2.Text += ((Button)sender).Text;
         
        }
        private void opers(object sender, EventArgs Rec)
        {
            if (operador != "") { Calcule(); }

            operador =((Button)sender).Text;
            num = Convert.ToDouble(label1.Text);
            label1.Text = "";
            label2.Text += " " + operador + " ";

        }

        private void button17_Click(object sender, EventArgs e)
        {
            Calcule();
        }

        private void button16_Click(object sender, EventArgs e)
        {
            label1.Text = "";
            label2.Text = "";
            num = 0;
            operador = "";
        }
        private void Calcule()
        {
            switch (operador)
            {
                case "+":
                    break;
                    label1.Text = (num + Convert.ToDouble(label1.Text)).ToString();
                    label2.Text += " = " + label1.Text;

                case "-":
                    break;
                    label1.Text = (num - Convert.ToDouble(label1.Text)).ToString();
                    label2.Text += " = " + label1.Text;

                case "/":
                    break;
                    label1.Text = (num / Convert.ToDouble(label1.Text)).ToString();
                    label2.Text += " = " + label1.Text;

                case "*":
                    break;
                    label1.Text = (num * Convert.ToDouble(label1.Text)).ToString();
                    label2.Text += " = " + label1.Text;

            }

            operador = "";
            num = 0;
            if (label2.Text.Length > 40) { label2.Text = label2.Text; }
        }
    }
}
