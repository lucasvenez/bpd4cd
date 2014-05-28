# Script to generate the analyzes of the selection step.
# Author: Lucas Venezian Povoa
###############################################################################
setwd("/home/lucas/workspace/BPD4CD/analyzes");

require("scatterplot3d")

d <- read.table("data/data_flow_matrix.csv", TRUE, ",","\"");

png("data/sparse_matrix_3d.png", width=309.921259843, height=220);

scatterplot3d(d$source, d$destination, d$variable, 
	pch=19^d[,4], angle=40, highlight.3d=F,
	col.axis="black", col.grid="lightgray", box=F,
	xlim=c(0,8), ylim=c(0,8), xlab="Source", ylab="Destination",
	zlab="Variable", color=d[,4]+16, cex=1.2, y.margin.add = 0.1,
	label.tick.marks=F, tick.marks=F, axis=T, type="p",
	cex.symbols=1, scale.y=1, zlim=c(0.5,3), cex.axis=1.2)

dev.off();

require("ggplot2")

d <- read.table("data/throughtput_execution_time.csv", TRUE, ",", "\"");

d$number_of_request_per_second = 
	factor(d$number_of_request_per_second, ordered = T, 
			levels=c(1,2,4,8,16,32,64,128,256,512), 
			labels = c(1,2,4,8,16,32,64,128,256,512));

png("data/throughput_time_execution.png", width=309.921259843, height=174.236220472)

print(ggplot(data=d, aes(x=factor(number_of_request_per_second), 
		y=bytes_per_second, colour=form, linetype=form, group=form)) +
	geom_line() + geom_point(size=1.2) +
	scale_linetype_discrete("Legend") +
	scale_colour_hue("Legend") +
	theme(
		text = element_text(size=14),
		legend.title=element_text("Legend"), 
		legend.direction = "horizontal", 
		legend.position = "top", 
		legend.box = "vertical") +
	ylab("MBytes per second") +
	xlab("Requests per second"));

dev.off();
