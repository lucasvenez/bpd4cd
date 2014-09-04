####
#
# Script employed for generating a representation of the sparse 
# matrix R used in the location selection framework.
#

setwd("/home/lucas/Dropbox/Master degree/Dissertation/Documents");

node_data <- read.csv2("node_data.csv");

nodes <- read.csv2("nodes.csv");
data <- paste(rep("v", 9), 1:9, sep = "");

node_data_cross <- merge(nodes, data, by = NULL)
node_data_cross <- cbind(node_data_cross, data.frame(relation = 0))

for (i in 1:nrow(node_data_cross)) {
	
	for (j in 1:nrow(node_data)) {
		
		if (as.character(node_data_cross[i,1]) == as.character(node_data[j,1]) && 
			as.character(node_data_cross[i,2]) == as.character(node_data[j,2])) {
			
			node_data_cross[i,3] = 1;
		} 
	}
}

require("ggplot2")

colnames(node_data_cross) <- c("node", "variable", "relation")

cbPalette <- c("#F0F0F0", "#E69F00", "#56B4E9", "#009E73", "#F0E442", "#0072B2", "#D55E00", "#CC79A7")

plot <- ggplot(node_data_cross, aes(x = factor(node, as.character(unique(node))), y = variable)) + 
		geom_tile(aes(fill = factor(relation)), colour = "white") + 
		scale_fill_manual("", values = cbPalette) +
		xlab("Atividade") + ylab("Item de dado") +
		theme(
			legend.position = "none",
			panel.background = element_rect(fill = "white"),
			axis.text.x = element_text(angle = 45, hjust = 1),
			text = element_text(size=12))

ggsave("./sparse-matrix-r.png", plot, units = "cm", width = 16, height = 7)
