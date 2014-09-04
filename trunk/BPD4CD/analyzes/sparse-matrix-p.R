###
#
#
#

setwd("/home/lucas/Dropbox/Master degree/Dissertation/Documents");

require("ggplot2");

node_ <- c("a1", "a2");
data_ <- c("v3", "v5");

persistence <- data.frame(node = node_, data = data_);

rm(node_, data_);

node <- read.csv2("nodes.csv");
data <- paste(rep("v", 9), 1:9, sep = "");

node_data <- merge(node, data.frame(data = data), by = NULL);
node_data <- cbind(node_data, data.frame(relation = 0));

colnames(node_data) <- c("node", "data", "relation");

rm(node, data);

node_data_ <- read.csv2("node_data.csv");

for (i in 1:nrow(node_data)) {
	
	for (j in 1:nrow(persistence)) {
		if (as.character(node_data[i,1]) == as.character(persistence[j,1]) && 
			as.character(node_data[i,2]) == as.character(persistence[j,2])) {
			
			node_data[i,3] = 2;
			
		}
	}
}

cbPalette <- c("#F0F0F0", "#E69F00", "#56B4E9", "#009E73", "#F0E442", "#0072B2", "#D55E00", "#CC79A7")

plot <- ggplot(node_data) + 
	geom_tile(
		aes(
			x = factor(node, as.character(unique(node))), 
			y = data, fill = factor(relation)), 
		colour = "white") +
	xlab("Atividade") + ylab("Item de dado") +
	scale_fill_manual(values = cbPalette) +
	theme(
		legend.position = "none",
		panel.background = element_rect(fill = "white"),
		axis.text.x = element_text(angle = 45, hjust = 1),
		text = element_text(size=12))
	
ggsave("sparse-matrix-p.png", plot, units = "cm", width = 16, height = 7);