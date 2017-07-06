using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using AtividadeEntity.localhost;

namespace AtividadeEntity
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            Service1 sv = new Service1();
            dataGridView1.DataSource = sv.listar();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            new FormCadastro().ShowDialog();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Service1 sv = new Service1();
            dataGridView1.DataSource = sv.listar();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            
            if (MessageBox.Show("Você realmente deseja excluir esse item?", "Alerta", MessageBoxButtons.OKCancel, MessageBoxIcon.Warning) == System.Windows.Forms.DialogResult.OK)
            {
                int index = dataGridView1.SelectedRows[0].Index;
                Service1 sv = new Service1();
                sv.excluir(index);
                MessageBox.Show("Excluído com Sucesso!", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else {
                MessageBox.Show("Exclusão Cancelada!", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Você realmente deseja Alterar esse item?", "Alerta", MessageBoxButtons.OKCancel, MessageBoxIcon.Warning) == System.Windows.Forms.DialogResult.OK)
            {
                new FormAlteracao(this).ShowDialog();
            }
            else {
                MessageBox.Show("Alteração Cancelada!", "Alerta", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }
    }
}
