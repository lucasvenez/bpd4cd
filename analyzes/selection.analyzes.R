##################################
##################################
##################################
setwd("/home/lucas/workspace/BPD4CD/analyzes");

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
