package com.vedruna.proyectomutimedia.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedruna.proyectomutimedia.R;
import com.vedruna.proyectomutimedia.model.Product;

import java.util.List;

/**
 * Adapter class to populate a ListView with Product items.
 */
public class ProductAdapter extends BaseAdapter {
    // Declaración de variables miembro
    private final List<Product> products; // Lista de productos
    private final Context context; // Contexto de la aplicación

    /**
     * Constructor for the ProductAdapter class.
     * @param context The application context.
     * @param products List of products.
     */
    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    // Sobrescritura del método getCount para obtener el número de elementos en la lista
    @Override
    public int getCount() {
        return products.size();
    }

    // Sobrescritura del método getItem para obtener un elemento en una posición específica
    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    // Sobrescritura del método getItemId para obtener el ID de un elemento en una posición específica
    @Override
    public long getItemId(int position) {
        return products.get(position).getProductID();
    }

    // Sobrescritura del método getView para obtener una vista que muestra los datos en una posición específica
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        // Si convertView es nulo, inflar la vista y asignarla a viewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.nameLabel = convertView.findViewById(R.id.nameLabel);
            viewHolder.nameText = convertView.findViewById(R.id.nameText);
            viewHolder.priceLabel = convertView.findViewById(R.id.priceLabel);
            viewHolder.priceText = convertView.findViewById(R.id.priceText);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder); // Establecer el viewHolder como una etiqueta en convertView
        } else {
            viewHolder = (ViewHolder) convertView.getTag(); // Si convertView no es nulo, obtener el viewHolder de su etiqueta
        }

        // Obtener el producto actual
        Product product = (Product) getItem(position);

        // Asignar los valores del producto a las vistas
        viewHolder.nameLabel.setText("Nombre: ");
        viewHolder.nameText.setText(product.getName());
        viewHolder.priceLabel.setText("Precio: ");
        viewHolder.priceText.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImageUrl()).into(viewHolder.imageView);

        return convertView; // Retornar la vista
    }

    /**
     * Inner static class ViewHolder holding references to views of a list item.
     */
    static class ViewHolder {
        TextView nameLabel;
        TextView nameText;
        TextView priceLabel;
        TextView priceText;
        ImageView imageView;
    }
}


