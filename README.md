# Predicting-BRCA-using-Cancer-Associated-Microbiome-Data

## Main Objective
The main aim of this project is to investigate whether microbiome data can discriminate between breast cancer versus normal samples using machine learning (*name the classification model after comparison*). Additionally, using the One Rule (OneR) classification model, the analysis seeks to answer whether a particular microbial taxa is significantly associated with BRCA. And therefore is it overrepresented in a population and hence more susceptible to BRCA.

The specific goals include:
1. Merging the the full raw counts data matrix with the metadata from Poore *et al*
2. Data preprocessing that include data manipulation, filtering and normalization
3. Exploratory Data Analysis (EDA) to visualize and summarize the data
4. Build classification model
    - Compare model performance using logistic regression and random forest
6. Perform prediction
7. Model evaluation using cross-fold validation and permiutations; and visualize resulting ROC curves
8. Perform statistical test to analyse difference between BRCA and normal samples

## Data
The microbial raw reads and metadata from the supplemental tables from the paper titled "Robustness of cancer microbiome signals over a broad range of methodological variation" by Sepich-Poore et al., 2024.

## References 
1. Sepich-Poore, G.D. et al. (2024) ‘Robustness of cancer microbiome signals over a broad range of methodological variation’, Oncogene, 43(15), pp. 1127–1148. doi:10.1038/s41388-024-02974-w.
2. Poore, G.D. et al. (2020) ‘Retracted article: Microbiome analyses of blood and tissues suggest cancer diagnostic approach’, Nature, 579(7800), pp. 567–574. doi:10.1038/s41586-020-2095-1. 

