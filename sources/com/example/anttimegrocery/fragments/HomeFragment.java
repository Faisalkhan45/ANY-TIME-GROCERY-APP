package com.example.anttimegrocery.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.activities.ShowAllActivity;
import com.example.anttimegrocery.adapters.CategoryAdapter;
import com.example.anttimegrocery.adapters.NewProductsAdapter;
import com.example.anttimegrocery.adapters.PopularProductsAdapter;
import com.example.anttimegrocery.models.CategoryModel;
import com.example.anttimegrocery.models.NewProductsModel;
import com.example.anttimegrocery.models.PopularProductsModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView catRecyclerview;
    TextView catShowAll;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;
    FirebaseFirestore db;
    LinearLayout linearLayout;
    RecyclerView newProductRecyclerview;
    TextView newProductShowAll;
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel> popularProductsModelList;
    RecyclerView popularRecyclerview;
    TextView popularShowAll;
    ProgressDialog progressDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        this.db = FirebaseFirestore.getInstance();
        this.progressDialog = new ProgressDialog(getActivity());
        this.catRecyclerview = (RecyclerView) root.findViewById(R.id.rec_category);
        this.newProductRecyclerview = (RecyclerView) root.findViewById(R.id.new_product_rec);
        this.popularRecyclerview = (RecyclerView) root.findViewById(R.id.popular_rec);
        this.catShowAll = (TextView) root.findViewById(R.id.category_see_all);
        this.popularShowAll = (TextView) root.findViewById(R.id.popular_see_all);
        TextView textView = (TextView) root.findViewById(R.id.newProducts_see_all);
        this.newProductShowAll = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.getContext(), ShowAllActivity.class));
            }
        });
        this.popularShowAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.getContext(), ShowAllActivity.class));
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) root.findViewById(R.id.home_layout);
        this.linearLayout = linearLayout2;
        linearLayout2.setVisibility(8);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(Integer.valueOf(R.drawable.banner1), "Discount On Shoes Items", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(Integer.valueOf(R.drawable.banner2), "Discount On Perfumes", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(Integer.valueOf(R.drawable.banner3), "70% OFF", ScaleTypes.CENTER_CROP));
        ((ImageSlider) root.findViewById(R.id.image_slider)).setImageList(slideModels);
        this.progressDialog.setTitle("Welcome To My Any Time Grocery App");
        this.progressDialog.setMessage("Please Wait....");
        this.progressDialog.setCanceledOnTouchOutside(false);
        this.progressDialog.show();
        this.catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.categoryModelList = new ArrayList();
        CategoryAdapter categoryAdapter2 = new CategoryAdapter(getContext(), this.categoryModelList);
        this.categoryAdapter = categoryAdapter2;
        this.catRecyclerview.setAdapter(categoryAdapter2);
        this.db.collection("Category").get().addOnCompleteListener(new HomeFragment$$ExternalSyntheticLambda0(this));
        this.newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.newProductsModelList = new ArrayList();
        NewProductsAdapter newProductsAdapter2 = new NewProductsAdapter(getContext(), this.newProductsModelList);
        this.newProductsAdapter = newProductsAdapter2;
        this.newProductRecyclerview.setAdapter(newProductsAdapter2);
        this.db.collection("NewProducts").get().addOnCompleteListener(new HomeFragment$$ExternalSyntheticLambda1(this));
        this.popularRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        this.popularProductsModelList = new ArrayList();
        PopularProductsAdapter popularProductsAdapter2 = new PopularProductsAdapter(getContext(), this.popularProductsModelList);
        this.popularProductsAdapter = popularProductsAdapter2;
        this.popularRecyclerview.setAdapter(popularProductsAdapter2);
        this.db.collection("AllProducts").get().addOnCompleteListener(new HomeFragment$$ExternalSyntheticLambda2(this));
        return root;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$0$com-example-anttimegrocery-fragments-HomeFragment  reason: not valid java name */
    public /* synthetic */ void m285lambda$onCreateView$0$comexampleanttimegroceryfragmentsHomeFragment(Task task) {
        if (task.isSuccessful()) {
            Iterator<QueryDocumentSnapshot> it = ((QuerySnapshot) task.getResult()).iterator();
            while (it.hasNext()) {
                this.categoryModelList.add((CategoryModel) it.next().toObject(CategoryModel.class));
                this.categoryAdapter.notifyDataSetChanged();
                this.linearLayout.setVisibility(0);
                this.progressDialog.dismiss();
            }
            return;
        }
        Toast.makeText(getActivity(), "" + task.getException(), 0).show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$1$com-example-anttimegrocery-fragments-HomeFragment  reason: not valid java name */
    public /* synthetic */ void m286lambda$onCreateView$1$comexampleanttimegroceryfragmentsHomeFragment(Task task) {
        if (task.isSuccessful()) {
            Iterator<QueryDocumentSnapshot> it = ((QuerySnapshot) task.getResult()).iterator();
            while (it.hasNext()) {
                this.newProductsModelList.add((NewProductsModel) it.next().toObject(NewProductsModel.class));
                this.newProductsAdapter.notifyDataSetChanged();
            }
            return;
        }
        Toast.makeText(getActivity(), "" + task.getException(), 0).show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$2$com-example-anttimegrocery-fragments-HomeFragment  reason: not valid java name */
    public /* synthetic */ void m287lambda$onCreateView$2$comexampleanttimegroceryfragmentsHomeFragment(Task task) {
        if (task.isSuccessful()) {
            Iterator<QueryDocumentSnapshot> it = ((QuerySnapshot) task.getResult()).iterator();
            while (it.hasNext()) {
                this.popularProductsModelList.add((PopularProductsModel) it.next().toObject(PopularProductsModel.class));
                this.popularProductsAdapter.notifyDataSetChanged();
            }
            return;
        }
        Toast.makeText(getActivity(), "" + task.getException(), 0).show();
    }
}
