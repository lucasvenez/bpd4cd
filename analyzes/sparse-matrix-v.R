###
#
# Script to generate the analyzes of the selection step.
#

require("plot3D")

setwd("/home/lucas/Dropbox/Master degree/Dissertation/Documents")

flow <- read.csv2("data_flow.csv");

nodes <- read.csv2("nodes.csv");
data  <- data.frame(data = paste(rep("v", 9), 1:9, sep = ""));

source_dest_var <- merge(
		data.frame(source = nodes[1]), 
		data.frame(destination = nodes[1]),
		by = NULL);

source_dest_var <- merge(source_dest_var, data, by = NULL);

source_dest_var <- merge(source_dest_var, data.frame(relation = 0), by = NULL);

colnames(source_dest_var) <- c("source", "destination", "data", "relation");

for (i in 1:nrow(source_dest_var)) {
	for (j in 1:nrow(flow)) {
		if (as.character(source_dest_var[i,1]) == as.character(flow[j,1]) && 
				as.character(source_dest_var[i,2]) == as.character(flow[j,2]) && 
				as.character(source_dest_var[i,3]) == as.character(flow[j,3])) {
			
			source_dest_var[i,4] = 1;
		}
	}
}

node_number <- data.frame(number = 1:nrow(nodes), node = as.character(nodes[,1]))
data_number <- data.frame(number = 1:nrow(data),  data = as.character(data[,1]))

png("../Images/sparse-matrix-3d.png", width = 604);

scatter3D(
		unlist(lapply(as.list(as.character(source_dest_var$source)), function(x) {node_number[node_number[2] == x, 1]})), 
		unlist(lapply(as.character(source_dest_var$destination),     function(x) {node_number[node_number[2] == x, 1]})), 
		unlist(lapply(as.character(source_dest_var$data),            function(x) {data_number[data_number[2] == x, 1]})),
		bty = "b2", type="n", theta = 45,
		col = ifelse(source_dest_var[,4] == 0, "grey", "red"), colvar = NULL, phi = 30,  pch = 19,
		cex = ifelse(source_dest_var[,4] == 0, .2, .35),
		xlab = "\nNó de origem", ylab = "\nNó de destino", zlab = "\nItem de dado")

dev.off();